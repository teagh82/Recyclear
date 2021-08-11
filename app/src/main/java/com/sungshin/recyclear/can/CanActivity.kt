package com.sungshin.recyclear.can

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ActivityCanBinding

class CanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val canListFragment = CanListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_can, canListFragment)
        transaction.commit()
    }
}