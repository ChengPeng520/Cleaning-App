package com.example.cleaningapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.cleaningapp.cleaner.view.shop.ShoppingCartFragment
import com.example.cleaningapp.databinding.ActivityCleanerBinding
import com.example.cleaningapp.share.TapPay
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData

class CleanerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCleanerBinding
    private lateinit var navHostFragment: NavHostFragment
    private val requestCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cleaner)
        navHostFragment =
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

    override fun onSupportNavigateUp(): Boolean {
        navHostFragment.navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        // 取得本機支付資訊(使用者同意支付的款項與信用卡資訊)
                        PaymentData.getFromIntent(data)?.let { paymentData ->
                            if (TapPay.getInstance().getPrimeFromTapPay(paymentData, this)) {
                                val intent = Intent()
                                intent.putExtra("result", true)
                                ShoppingCartFragment().onActivityResult(100, resultCode, intent)
                            }
                        }
                    }
                }
                AutoResolveHelper.RESULT_ERROR -> {
                    Log.d("錯誤", "錯誤")
                }
                else -> {}
            }
        }
    }
}