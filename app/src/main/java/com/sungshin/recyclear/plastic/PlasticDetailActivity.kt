package com.sungshin.recyclear.plastic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ActivityPlasticDetailBinding

class PlasticDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlasticDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlasticDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewPlasticDate.text = intent.getStringExtra("detect_date")
        binding.textviewPlasticPercent.text = intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewPlasticDetail)
    }
}