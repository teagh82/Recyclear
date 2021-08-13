package com.sungshin.recyclearuser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sungshin.recyclearuser.my.MyActivity
import com.sungshin.recyclearuser.databinding.ActivityMainBinding
import com.sungshin.recyclearuser.news.NewsActivity
import com.sungshin.recyclearuser.point.PointActivity
import com.sungshin.recyclearuser.recycle.RecycleActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

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

    }
}