package com.sungshin.recyclear

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sungshin.recyclear.can.CanActivity
import com.sungshin.recyclear.databinding.ActivityMainBinding
import com.sungshin.recyclear.glass.GlassActivity
import com.sungshin.recyclear.metal.MetalActivity
import com.sungshin.recyclear.paper.PaperActivity
import com.sungshin.recyclear.plastic.PetActivity
import com.sungshin.recyclear.vinyl.VinylActivity


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickBtns()
    }

    private fun onClickBtns(){
        binding.buttonMainCan.setOnClickListener {
            val intent = Intent(this@MainActivity, CanActivity::class.java)
            startActivity(intent)
            Log.d("button", "CAN")
        }
        binding.buttonMainMetal.setOnClickListener {
            val intent = Intent(this@MainActivity, MetalActivity::class.java)
            startActivity(intent)
            Log.d("button", "METAL")
        }
        binding.buttonMainPaper.setOnClickListener {
            val intent = Intent(this@MainActivity, PaperActivity::class.java)
            startActivity(intent)
            Log.d("button", "PAPER")
        }
        binding.buttonMainPet.setOnClickListener {
            val intent = Intent(this@MainActivity, PetActivity::class.java)
            startActivity(intent)
            Log.d("button", "PET")
        }
        binding.buttonMainVinyl.setOnClickListener {
            val intent = Intent(this@MainActivity, VinylActivity::class.java)
            startActivity(intent)
            Log.d("button", "VINYL")
        }
        binding.buttonMainGlass.setOnClickListener {
            val intent = Intent(this@MainActivity, GlassActivity::class.java)
            startActivity(intent)
            Log.d("button", "GLASS")
        }
        binding.buttonMainCheck.setOnClickListener {
            val intent = Intent(this@MainActivity, CheckActivity::class.java)
            startActivity(intent)
            Log.d("button", "CHECK")
        }
    }
}