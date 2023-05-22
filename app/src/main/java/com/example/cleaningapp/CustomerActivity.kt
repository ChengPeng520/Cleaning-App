package com.example.cleaningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.cleaningapp.databinding.ActivityCustomerBinding

class CustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.customer_nav_host_fragment) as NavHostFragment
        val configuration = AppBarConfiguration(binding.bvnCustomer.menu)

        NavigationUI.setupActionBarWithNavController(
            this,
            navHostFragment.navController,
            configuration
        )

        NavigationUI.setupWithNavController(
            binding.bvnCustomer,
            navHostFragment.navController
        )
    }
    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }
}