package com.example.cleaningapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
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

        initView()
        initNavigation()
    }

    private fun initView() {
        // 隱藏標題列
        supportActionBar?.hide()
        // 設置自訂義toolbar
        setSupportActionBar(binding.customerToolbar)
        // 隱藏App名稱
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    private fun initNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.customer_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bvnCustomer.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.csHomePageFragment -> {
                    navController.navigate(R.id.csHomePageFragment)
                    true
                }
                R.id.csCreateOrderFragment -> {
                    navController.navigate(R.id.csCreateOrderFragment)
                    true
                }
                R.id.historicalorderFragment -> {
                    navController.navigate(R.id.historicalorderFragment)
                    true
                }
                R.id.csUserPageFragment -> {
                    navController.navigate(R.id.csUserPageFragment)
                    true
                }
                else -> false
            }
        }
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