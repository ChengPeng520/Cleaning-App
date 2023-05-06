package com.example.cleaningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.cleaningapp.databinding.ActivityCleanerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CleanerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCleanerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cleaner)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val bvn = findViewById<BottomNavigationView>(R.id.bvn_cleaner)

        NavigationUI.setupWithNavController(
            bvn,
            navHostFragment.navController
        )

        navHostFragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->

        }
    }
}