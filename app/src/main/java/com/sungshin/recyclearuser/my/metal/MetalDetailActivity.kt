package com.sungshin.recyclearuser.my.metal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ActivityMetalDetailBinding

class MetalDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMetalDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewMetalDate.text = intent.getStringExtra("detect_date")
        binding.textviewMetalPercent.text = "정확도 : " + intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewMetalDetail)
    }
}