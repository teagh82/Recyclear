package com.sungshin.recyclearuser.point

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sungshin.recyclearuser.MainActivity
import com.sungshin.recyclearuser.R
import com.sungshin.recyclearuser.databinding.ActivityPointBinding
import com.sungshin.recyclearuser.my.MyActivity
import com.sungshin.recyclearuser.point.pointlist.PointListAdapter
import com.sungshin.recyclearuser.point.pointlist.PointListInfo
import com.sungshin.recyclearuser.utils.FirebaseUtil
import com.sungshin.recyclearuser.utils.MyPref

class PointActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPointBinding

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var curPoint = ""
    var addPoint = 0
    val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]
    private val pointListAdapter: PointListAdapter by lazy{ PointListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPoints()

        val pointListFragment = PointListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_point, pointListFragment)
        transaction.commit()
        onClickButton()
    }

    private fun onClickButton() {
        binding.getPointBtn.setOnClickListener {
            moveData()

            addPoint = datas.size * 10
            var data = datas.toString()
            Log.d("POINTS", "addPoint: $addPoint $data")


            if (curPoint != "") {
                val changePoint = (curPoint.toInt() + addPoint).toString()
                val pointRef =
                    database.getReference("User").child(saveIDdata)
                pointRef.child("points").setValue(changePoint)

                Toast.makeText(this, "$addPoint 포인트가 적립되었습니다.\n" +
                        "현재 포인트는 $changePoint 점 입니다.", Toast.LENGTH_SHORT).show()
                Log.d("POINTS", "add points db done")
            }

            else {
                Log.d("POINTS", "no cur point err")
            }

            finish()
        }
    }

    private fun getPoints() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.hasChild("points")) {
                    curPoint = dataSnapshot.child("points").getValue(String::class.java).toString()
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

        Log.d("POINTS", curPoint)
    }

    private fun moveData() {
        for (data in datas) {
            var checkedRef = database.getReference("User").child(saveIDdata).child("checked")
                .child(data.detect_class).child(data.detect_image_name)
            checkedRef.child("date").setValue(data.detect_date)
            checkedRef.child("image").setValue(data.detect_image)
            checkedRef.child("pred").setValue(data.detect_pred)

            database.getReference("User").child(saveIDdata).child("unchecked")
                .child(data.detect_class).child(data.detect_image_name).removeValue()
            .addOnSuccessListener {
                Log.d("POINTS", "eraseData success")
            }
            .addOnFailureListener {
                Log.d("POINTS", "eraseData failed")
            }
        }

        mutableListOf<PointListInfo>().clear()
    }
}