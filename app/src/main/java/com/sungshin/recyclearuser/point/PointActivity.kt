package com.sungshin.recyclearuser.point

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclearuser.R
import com.sungshin.recyclearuser.databinding.ActivityPointBinding

class PointActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPointBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val pointListFragment = PointListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_point, pointListFragment)
        transaction.commit()
    }
}