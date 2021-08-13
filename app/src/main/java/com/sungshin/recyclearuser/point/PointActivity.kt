package com.sungshin.recyclearuser.point

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclearuser.databinding.ActivityPointBinding

class PointActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}