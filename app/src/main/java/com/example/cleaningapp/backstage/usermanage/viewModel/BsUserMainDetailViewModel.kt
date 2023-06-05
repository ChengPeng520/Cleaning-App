package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.usermanage.model.*
import com.example.cleaningapp.share.requestTask

/**
 * 單一使用者資料處理
 */
class BsUserMainDetailViewModel : ViewModel() {
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>() }

    fun fetchMemberInfo(member: Member) {
        when (member.status) {
            1 -> {
                requestTask<AccountCustomer>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/1/${member.id}",
                    method = "GET"
                )?.let {
                    user.value = User(
                        cleanerId = null,
                        customerId = it.customerId,
                        backstageId = null,
                        email = it.email,
                        name = it.name,
                        photo = it.userPhoto,
                        phone = it.phone,
                        gender = it.gender,
                        introduction = it.introduction,
                        timeCreate = it.timeCreate,
                        timeUpdate = it.timeUpdate,
                        role = "一般用戶",
                        suspend = it.suspend,
                        verify = false,
                        identifyNumber = "",
                        idCardFront = null,
                        idCardBack = null,
                        crc = null
                    )
                }
            }
            2 -> {
                requestTask<AccountCleaner>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/2/${member.id}",
                    method = "GET"
                )?.let {
                    user.value = User(
                        cleanerId = it.cleanerId,
                        customerId = null,
                        backstageId = null,
                        email = it.email,
                        name = it.name,
                        photo = it.userPhoto,
                        phone = it.phone,
                        gender = it.gender,
                        introduction = it.introduction,
                        timeCreate = it.timeCreate,
                        timeUpdate = it.timeUpdate,
                        role = "清潔人員",
                        suspend = it.suspend,
                        verify = it.verify,
                        identifyNumber = it.identifyNumber,
                        idCardFront = it.userIdCardFront,
                        idCardBack = it.userIdCardBack,
                        crc = it.userCrc
                    )
                }
            }
            3 -> {
                requestTask<AccountBackstage>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/3/${member.id}",
                    method = "GET"
                )?.let {
                    user.value = User(
                        cleanerId = null,
                        customerId = null,
                        backstageId = it.backstageId,
                        email = it.account,
                        name = it.name,
                        photo = null,
                        phone = "",
                        gender = 0,
                        introduction = "",
                        timeCreate = it.timeCreate,
                        timeUpdate = it.timeUpdate,
                        role = "後台人員",
                        suspend = it.suspend,
                        verify = false,
                        identifyNumber = "",
                        idCardFront = null,
                        idCardBack = null,
                        crc = null
                    )
                }
            }
        }
    }
}