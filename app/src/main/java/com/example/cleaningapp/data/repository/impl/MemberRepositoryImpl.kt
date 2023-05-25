package com.example.cleaningapp.data.repository.impl

import android.app.Application
import com.example.cleaningapp.cleaner.uistate.MemberInfoUiState
import com.example.cleaningapp.data.datasource.MemberLocalDataSource
import com.example.cleaningapp.data.IMemberRepository
import com.example.cleaningapp.data.pojo.MemberApiModel

class MemberRepositoryImpl(private val memberLocalDataSource: MemberLocalDataSource) :
    IMemberRepository {
    // 資料轉成ui層使用
    /*
    注意：一般來說，如果存放區只包含一個資料來源，且不需要依賴其他存放區，開發人員可以將存放區和資料來源的責任併入存放區類別。
    併入後，如果存放區需要在日後的應用程式中處理其他來源的資料，不要忘記分割功能。
     */
    override suspend fun fetchMemberInfo(application: Application): MemberInfoUiState {
        val member = memberLocalDataSource.fetchMemberInfo(application = application)
        return MemberInfoUiState(
            member.name.toString(),
            member.gender,
            member.identityNumber.toString(),
            member.phone.toString(),
            member.introduction.toString()
        )
    }

    override suspend fun saveMemberInfo(
        application: Application,
        memberInfo: MemberInfoUiState
    ): String {
        val memberApiModel = MemberApiModel(
            memberInfo.name,
            memberInfo.gender,
            memberInfo.identityNumber,
            memberInfo.phone,
            memberInfo.introduction
        )
        return memberLocalDataSource.saveMemberInfo(application, memberApiModel)
    }
}