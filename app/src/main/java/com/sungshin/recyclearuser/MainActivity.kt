package com.sungshin.recyclearuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sungshin.recyclearuser.databinding.ActivityMainBinding
import com.sungshin.recyclearuser.my.MyActivity
import com.sungshin.recyclearuser.news.NewsActivity
import com.sungshin.recyclearuser.point.PointActivity
import com.sungshin.recyclearuser.recycle.RecycleActivity
import com.sungshin.recyclearuser.utils.MyPref
import java.io.PrintWriter
import java.net.Socket


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var backKeyPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickBtns()
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
            val thread = ClientThread()
            thread.start()

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

    protected class ClientThread : Thread() {
        override fun run() {
            val host = "3.38.63.94"
            val port = 9724
            try {
                val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]
                val socket = Socket(host, port)
                Log.e("sck", "suc")

                val out = PrintWriter(socket.getOutputStream(), true)
                out.print(saveIDdata)
                out.flush()

                socket.close()
            }

            catch (e: Exception) {
                Log.e("sck", "fail")
                e.printStackTrace()
            }
        }
    }
}