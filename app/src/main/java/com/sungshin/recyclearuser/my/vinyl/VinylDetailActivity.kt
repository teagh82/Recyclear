package com.sungshin.recyclearuser.my.vinyl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ActivityVinylDetailBinding

class VinylDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVinylDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVinylDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewVinylDate.text = intent.getStringExtra("detect_date")
        binding.textviewVinylPercent.text = intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewVinylDetail)
    }
}