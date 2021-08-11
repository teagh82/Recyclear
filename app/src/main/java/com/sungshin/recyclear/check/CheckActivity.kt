package com.sungshin.recyclear.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ActivityCheckBinding

class CheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val checkListFragment = CheckListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_check, checkListFragment)
        transaction.commit()
    }
}