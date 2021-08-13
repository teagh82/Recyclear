package com.sungshin.recyclearuser.my.can

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclearuser.R
import com.sungshin.recyclearuser.databinding.ActivityCanBinding

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