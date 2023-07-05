package com.example.cleaningapp.share

import okhttp3.OkHttpClient

object OkHttp {
    private var client: OkHttpClient? = null

    fun getClient(): OkHttpClient {
        if (client == null) {
            client = OkHttpClient().newBuilder().build()
        }
        return client!!
    }
}