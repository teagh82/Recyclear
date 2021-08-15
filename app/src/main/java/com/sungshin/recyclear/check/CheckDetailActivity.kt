package com.sungshin.recyclear.check

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ActivityCheckDetailBinding

var labelImg : String = ""
var labelImgName : String = ""
var className : String = ""

class CheckDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textviewCheckDate.text = intent.getStringExtra("detect_date")
        binding.textviewCheckPercent.text = intent.getStringExtra("detect_percent")
        Glide.with(this)
            .load(intent.getStringExtra("detect_image"))
            .into(binding.imageviewCheckDetail)

        labelImg = intent.getStringExtra("detect_image").toString()
        labelImgName = intent.getStringExtra("detect_image_name").toString()
        className = intent.getStringExtra("detect_class").toString()

        binding.buttonCheckLabeling.setOnClickListener {
            val intent = Intent(this@CheckDetailActivity, LabelingActivity::class.java)
            startActivity(intent)
            Log.d("button", "LABELING")
        }
    }
}