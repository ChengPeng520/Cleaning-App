package com.example.cleaningapp.datasource

import android.app.Application
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class MemberApiModel(
    val name: String?,
    val gender: Int,
    val identityNumber: String?,
    val phone: String?,
    val introduction: String?
)

class MemberLocalDataSource {

    suspend fun fetchLatestMember(application: Application): MemberApiModel =
        withContext(Dispatchers.IO) {
            val preferences =
                application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE)
            return@withContext MemberApiModel(
                preferences.getString("name", ""),
                preferences.getInt("gender", 0),
                preferences.getString("identityNumber", ""),
                preferences.getString("phone", ""),
                preferences.getString("introduction", "")
            )
        }
}