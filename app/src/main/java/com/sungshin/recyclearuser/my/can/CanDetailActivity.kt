package com.sungshin.recyclearuser.my.can

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ActivityCanDetailBinding

class CanDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCanDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewCanDate.text = intent.getStringExtra("detect_date")
        binding.textviewCanPercent.text = intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewCanDetail)
    }
}