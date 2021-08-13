package com.sungshin.recyclearuser.my.glass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclearuser.R
import com.sungshin.recyclearuser.databinding.ActivityGlassBinding

class GlassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlassBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val glassListFragment = GlassListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_glass, glassListFragment)
        transaction.commit()
    }
}