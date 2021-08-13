package com.sungshin.recyclearuser.recycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclearuser.databinding.ActivityRecycleBinding

class RecycleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecycleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecycleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}