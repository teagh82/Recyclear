package com.sungshin.recyclearuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sungshin.recyclearuser.databinding.ActivityMainBinding
import com.sungshin.recyclearuser.my.MyActivity
import com.sungshin.recyclearuser.news.NewsActivity
import com.sungshin.recyclearuser.point.PointActivity
import com.sungshin.recyclearuser.recycle.RecycleActivity
import com.sungshin.recyclearuser.utils.FirebaseUtil
import com.sungshin.recyclearuser.utils.MyPref
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var backKeyPressedTime: Long = 0

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickBtns()
        getPoints()
    }

    private fun onClickBtns(){
        binding.buttonMainDiary.setOnClickListener {
            val intent = Intent(this@MainActivity, MyActivity::class.java)
            startActivity(intent)
            Log.d("button", "MY")
        }
        binding.buttonMainPoint.setOnClickListener {
            val intent = Intent(this@MainActivity, PointActivity::class.java)
            startActivity(intent)
            Log.d("button", "POINT")
        }
        binding.buttonMainRecycle.setOnClickListener {
            val intent = Intent(this@MainActivity, RecycleActivity::class.java)
            startActivity(intent)
            Log.d("button", "RECYCLE")
        }
        binding.buttonMainNews.setOnClickListener {
            val intent = Intent(this@MainActivity, NewsActivity::class.java)
            startActivity(intent)
            Log.d("button", "NEWS")
        }
        binding.buttonMainDetect.setOnClickListener{
            val requestRef = database.getReference("Request")
            val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]
            requestRef.setValue(saveIDdata)
            Log.d("REQUEST", requestRef.get().toString())
            Toast.makeText(this, "분류를 시작합니다.", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG).show()
        }

        else {
            finish()
        }
    }

    private fun getPoints() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChild("points")) {
                    var curPoint = dataSnapshot.child("points").getValue(String::class.java).toString()

                    binding.textviewMainPoint.text = curPoint
                    Log.w("POINTS", "curPoint: $curPoint")
                }

                else {
                    Log.w("POINTS", "no hasChild")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("POINTS", "loadPost:onCancelled", databaseError.toException())
            }
        }

        val pointRef = database.getReference("User").child(saveIDdata)
        pointRef.addValueEventListener(postListener)
    }
}