package com.sungshin.recyclearuser.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sungshin.recyclearuser.my.can.CanActivity
import com.sungshin.recyclearuser.databinding.ActivityMyBinding
import com.sungshin.recyclearuser.my.glass.GlassActivity
import com.sungshin.recyclearuser.my.metal.MetalActivity
import com.sungshin.recyclearuser.my.paper.PaperActivity
import com.sungshin.recyclearuser.my.plastic.PetActivity
import com.sungshin.recyclearuser.my.vinyl.VinylActivity

class MyActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClickBtns()
    }

    private fun onClickBtns(){
        binding.buttonMyCan.setOnClickListener {
            val intent = Intent(this@MyActivity, CanActivity::class.java)
            startActivity(intent)
            Log.d("button", "CAN")
        }
        binding.buttonMyMetal.setOnClickListener {
            val intent = Intent(this@MyActivity, MetalActivity::class.java)
            startActivity(intent)
            Log.d("button", "METAL")
        }
        binding.buttonMyPaper.setOnClickListener {
            val intent = Intent(this@MyActivity, PaperActivity::class.java)
            startActivity(intent)
            Log.d("button", "PAPER")
        }
        binding.buttonMyPet.setOnClickListener {
            val intent = Intent(this@MyActivity, PetActivity::class.java)
            startActivity(intent)
            Log.d("button", "PET")
        }
        binding.buttonMyVinyl.setOnClickListener {
            val intent = Intent(this@MyActivity, VinylActivity::class.java)
            startActivity(intent)
            Log.d("button", "VINYL")
        }
        binding.buttonMyGlass.setOnClickListener {
            val intent = Intent(this@MyActivity, GlassActivity::class.java)
            startActivity(intent)
            Log.d("button", "GLASS")
        }
    }
}