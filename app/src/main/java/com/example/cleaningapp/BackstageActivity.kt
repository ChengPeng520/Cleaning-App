package com.example.cleaningapp

import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.example.cleaningapp.databinding.ActivityBackstageBinding

class BackstageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBackstageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBackstageBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        //啟動actionbar
        setUpActionBar()
        initDrawer()

        val navHostFragment =
            //讓nav_host_fragment支援像NavHostFragment 的導航功能
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        //可以配合點選抽屜項目切換頁面
        NavigationUI.setupWithNavController(
            binding.backstageNavigationView,
            navHostFragment.navController
        )
    }

    @SuppressLint("RestrictedApi")
    private fun setUpActionBar() {
        //設定ActionBar的標題列左上角可以加上按鈕
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
    }

    //初始化抽梯的選單功能
    private fun initDrawer() {
        //設定ActionBarDrawerToggle監聽器,監聽抽屜開關的狀態
        with(binding) {
            val actionBarDrawerToggle =
                ActionBarDrawerToggle(
                this@BackstageActivity,
                drawerLayout, R.string.open, R.string.close
            )
            drawerLayout.addDrawerListener(actionBarDrawerToggle)
            //將左上角的menu圖示動畫轉換成返回鈕同步化
            actionBarDrawerToggle.syncState()
        }
    }

    // 點擊標題列上的按鈕會呼叫此方法,處理開關抽屜的狀態
    override  fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                with(binding) {
                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        drawerLayout.openDrawer(GravityCompat.START)
                    }
                }
                return true
            }
        }
        // 如果不是點選回主屏幕圖標,則返回onOptionItemSelected處理其他點選項目操作
        return super.onOptionsItemSelected(item)
    }
}


