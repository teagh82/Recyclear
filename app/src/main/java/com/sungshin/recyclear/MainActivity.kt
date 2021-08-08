package com.sungshin.recyclear

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sungshin.recyclear.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        onClickBtns()
    }

    fun onClickBtns(){
        binding.buttonMainCan.setOnClickListener {
            val intent = Intent(this@MainActivity, CanActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMainMetal.setOnClickListener {
            val intent = Intent(this@MainActivity, MetalActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMainPaper.setOnClickListener {
            val intent = Intent(this@MainActivity, PaperActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMainPet.setOnClickListener {
            val intent = Intent(this@MainActivity, PetActivity::class.java)
            startActivity(intent)
        }
        binding.buttonMainVinyl.setOnClickListener {
            val intent = Intent(this@MainActivity, VinylActivity::class.java)
            startActivity(intent)
        }
    }
}