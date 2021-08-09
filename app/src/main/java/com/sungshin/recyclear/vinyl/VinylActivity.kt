package com.sungshin.recyclear.vinyl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ActivityVinylBinding

class VinylActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVinylBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVinylBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vinylListFragment = VinylListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_vinyl, vinylListFragment)
        transaction.commit()
    }
}