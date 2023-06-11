package com.example.cleaningapp

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.cleaningapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        initView()
    }

    private fun initView() {
        // 隱藏標題列
        supportActionBar?.hide()
        // 設置自訂義toolbar
        setSupportActionBar(binding.loginToolbar)
        // 隱藏App名稱
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    // FCM傳送通知中, API 33開始需要加上requestPermissionLauncher
    override fun onStart() {
        super.onStart()
        this.findViewById<Toolbar>(R.id.login_toolbar).visibility = View.GONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private var requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            Log.d("myTag", "granted: $granted")
        }
}