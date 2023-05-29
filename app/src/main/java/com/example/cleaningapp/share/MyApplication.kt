package com.example.cleaningapp.share

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CustomerSharePreferencesUtils.init(this)
        CleanerSharedPreferencesUtils.init(this)
    }
}