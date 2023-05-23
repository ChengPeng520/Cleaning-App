package com.example.cleaningapp.repository

import com.example.cleaningapp.datasource.MemberApiModel
import com.example.cleaningapp.datasource.MemberLocalDataSource
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.sync.Mutex

interface IMemberRepository {
    suspend fun fetchLatestMember(): MemberApiModel
}

//class MemberRepositoryImpl(private val memberLocalDataSource: MemberLocalDataSource) :
//    IMemberRepository {
//
//    override suspend fun fetchLatestMember(): MemberApiModel {
//        val mutex = Mutex()
//        val ram: MemberApiModel =
//    }
//}