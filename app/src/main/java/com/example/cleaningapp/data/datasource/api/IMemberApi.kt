package com.example.cleaningapp.data.datasource.api

import android.app.Application
import com.example.cleaningapp.data.pojo.MemberApiModel

interface IMemberApi {
    fun fetchMemberInfo(application: Application): MemberApiModel
    fun saveMemberInfo(application: Application, memberInfo: MemberApiModel): String
}