package com.example.cleaningapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.example.cleaningapp.databinding.ActivityCleanerBinding
import com.example.cleaningapp.share.GetPrimeCallback
import com.example.cleaningapp.share.TapPay
import com.google.android.gms.wallet.AutoResolveHelper
import com.google.android.gms.wallet.PaymentData

class CleanerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCleanerBinding
    private val viewModel: CleanerViewModel by viewModels()
    private lateinit var navHostFragment: NavHostFragment
    private val requestCode = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cleaner)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initView()
        initNavigation()
    }

    private fun initView() {
        // 隱藏標題列
        supportActionBar?.hide()
        // 設置自訂義toolbar
        setSupportActionBar(binding.cleanerToolbar)
        // 隱藏App名稱
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.cleaner_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bvnCleaner.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.cleanerFrontFragment -> {
                    navController.navigate(R.id.cleanerFrontFragment)
                    true
                }
                R.id.vicky_order_conductFragment -> {
                    navController.navigate(R.id.vicky_order_conductFragment)
                    true
                }
                R.id.shopFragment -> {
                    navController.navigate(R.id.shopFragment)
                    true
                }
                R.id.memberFragment -> {
                    navController.navigate(R.id.memberFragment)
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
        viewModel.setResult(false)
    }
}