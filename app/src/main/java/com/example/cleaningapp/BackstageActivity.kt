package com.example.cleaningapp

import android.annotation.SuppressLint
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.example.cleaningapp.databinding.ActivityBackstageBinding
import com.example.cleaningapp.login.controller.LoginFragment
import com.example.cleaningapp.share.CustomerSharePreferencesUtils

class BackstageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBackstageBinding
    @SuppressLint("SuspiciousIndentation")
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
//                NavigationUI.setupWithNavController(
//                    binding.backstageNavigationView,
//                    navHostFragment.navController
//                )


        //使用setNavigationItemSelectedListener監測抽屜內的項目,binding.backstageNavigationView 抽屜的畫面
        binding.backstageNavigationView.setNavigationItemSelectedListener { item ->
            val navController = navHostFragment.navController
            when(item.itemId){
                //登出選項
                R.id.logOut -> {
                    val intent = Intent(this@BackstageActivity, LoginActivity::class.java)
                    startActivity(intent)
                    this.finish()
                    fun logout() {
                        CustomerSharePreferencesUtils.logout()
                    }
                }
                R.id.bsUserMainFragment ->{
                    navController.navigate(R.id.bsUserMainFragment)
                }
                R.id.orderManageFragment2 ->{
                    navController.navigate(R.id.orderManageFragment2)
                }
                R.id.bsCompMainFragment ->{
                    navController.navigate(R.id.bsCompMainFragment)
                }
                R.id.backstageCouponSearchFragment->{
                    navController.navigate(R.id.backstageCouponSearchFragment)
                }
                R.id.bsShopMainFragment ->{
                    navController.navigate(R.id.bsShopMainFragment)
                }
            }
            //抽屜開關
            with(binding) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }
            }
//            Toast.makeText(this@BackstageActivity, item.itemId.toString(), Toast.LENGTH_LONG).show()  測試用
            true
        }
    }


    private fun setUpActionBar() {
        //設定ActionBar的標題列左上角可以加上按鈕
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun initDrawer() {

        //設定ActionBarDrawerToggle監聽器,監聽抽屜開關的狀態
        with(binding) {
            val actionBarDrawerToggle =
                ActionBarDrawerToggle(
                    this@BackstageActivity,
                    drawerLayout, R.string.open, R.string.close
                )
            drawerLayout.addDrawerListener(actionBarDrawerToggle)
            //將左上角的menu圖示動畫轉換成返回同步化
            actionBarDrawerToggle.syncState()
        }
    }

//     點擊標題列上的漢堡會打開抽屜,處理開關抽屜的狀態,
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

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


