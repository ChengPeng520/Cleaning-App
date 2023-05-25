package com.example.cleaningapp.data.datasource

import android.app.Application
import com.example.cleaningapp.data.datasource.api.IMemberApi
import com.example.cleaningapp.data.pojo.MemberApiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MemberLocalDataSource(private val memberApi: IMemberApi) {
    suspend fun fetchMemberInfo(application: Application): MemberApiModel =
        withContext(Dispatchers.IO) {
            memberApi.fetchMemberInfo(application)
        }

    suspend fun saveMemberInfo(application: Application, memberInfo: MemberApiModel): String =
        withContext(Dispatchers.IO) {
            memberApi.saveMemberInfo(application, memberInfo)
        }
}