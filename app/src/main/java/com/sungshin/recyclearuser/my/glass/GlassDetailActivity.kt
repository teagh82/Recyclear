package com.sungshin.recyclearuser.my.glass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ActivityGlassDetailBinding

class GlassDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlassDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewGlassDate.text = intent.getStringExtra("detect_date")
        binding.textviewGlassPercent.text = "정확도 : " + intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewGlassDetail)
    }
}