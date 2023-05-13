package com.example.cleaningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.cleaningapp.databinding.ActivityCleanerBinding

class CleanerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCleanerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cleaner)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.cleaner_nav_host_fragment) as NavHostFragment
        val configuration = AppBarConfiguration(binding.bvnCleaner.menu)

        NavigationUI.setupActionBarWithNavController(
            this,
            navHostFragment.navController,
            configuration
        )

        NavigationUI.setupWithNavController(
            binding.bvnCleaner,
            navHostFragment.navController
        )
    }
}