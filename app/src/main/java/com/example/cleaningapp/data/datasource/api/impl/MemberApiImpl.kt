package com.example.cleaningapp.data.datasource.api.impl

import android.app.Application
import android.content.Context
import com.example.cleaningapp.data.datasource.api.IMemberApi
import com.example.cleaningapp.data.pojo.MemberApiModel

class MemberApiImpl : IMemberApi {
    override fun fetchMemberInfo(application: Application): MemberApiModel {
        val preferences =
            application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE)
        return MemberApiModel(
            preferences.getString("name", ""),
            preferences.getInt("gender", 0),
            preferences.getString("identityNumber", ""),
            preferences.getString("phone", ""),
            preferences.getString("introduction", "")
        )
    }

    override fun saveMemberInfo(application: Application, memberInfo: MemberApiModel): String {
        return try {
            application.getSharedPreferences("AccountCleaner", Context.MODE_PRIVATE).edit()
                .putString("name", memberInfo.name)
                .putInt("gender", memberInfo.gender)
                .putString("identityNumber", memberInfo.identityNumber)
                .putString("phone", memberInfo.phone)
                .putString("introduction", memberInfo.introduction)
                .apply()
            "儲存成功"
        } catch (e: Exception) {
            "儲存失敗"
        }
    }
}