package com.example.cleaningapp.data

import android.app.Application
import com.example.cleaningapp.cleaner.uistate.MemberInfoUiState

interface IMemberRepository {
    suspend fun fetchMemberInfo(application: Application): MemberInfoUiState
    suspend fun saveMemberInfo(application: Application, memberInfo: MemberInfoUiState): String
}