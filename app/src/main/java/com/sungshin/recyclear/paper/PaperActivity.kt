package com.sungshin.recyclear.paper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ActivityPaperBinding

class PaperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaperBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paperListFragment = PaperListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_paper, paperListFragment)
        transaction.commit()
    }
}