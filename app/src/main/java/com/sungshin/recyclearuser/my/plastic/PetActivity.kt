package com.sungshin.recyclearuser.my.plastic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungshin.recyclearuser.R
import com.sungshin.recyclearuser.databinding.ActivityPetBinding

class PetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plasticListFragment = PlasticListFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container_pet, plasticListFragment)
        transaction.commit()
    }
}