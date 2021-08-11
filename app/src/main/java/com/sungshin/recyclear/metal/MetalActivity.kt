package com.sungshin.recyclear.metal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ActivityMetalBinding

class MetalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMetalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMetalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val metalListFragment = MetalListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_metal, metalListFragment)
        transaction.commit()
    }
}