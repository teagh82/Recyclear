package com.sungshin.recyclear.paper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ActivityPaperDetailBinding

class PaperDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaperDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaperDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewPaperDate.text = intent.getStringExtra("detect_date")
        binding.textviewPaperPercent.text = "정확도 : " + intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewPaperDetail)
    }
}