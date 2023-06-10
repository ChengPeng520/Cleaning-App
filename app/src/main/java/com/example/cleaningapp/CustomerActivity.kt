package com.example.cleaningapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.cleaningapp.databinding.ActivityCustomerBinding
import com.example.cleaningapp.share.GetPrimeCallback
import com.example.cleaningapp.share.TapPay
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData

class CustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerBinding
    private val viewModel: CustomerViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private val requestCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        // 取得本機支付資訊(使用者同意支付的款項與信用卡資訊)
                        PaymentData.getFromIntent(data)?.let { paymentData ->
                            TapPay.getInstance()
                                .getPrimeFromTapPay(paymentData, this, object : GetPrimeCallback {
                                    override fun onGetPrimeResult(result: Boolean) {
                                        viewModel.setResult(result)
                                    }
                                })
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