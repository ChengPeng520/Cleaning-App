package com.example.cleaningapp.backstage.usermanage.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.*
import com.example.cleaningapp.share.BackstageSharedPreferencesUtils
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

/**
 * 單一使用者資料處理
 */
class BsUserMainDetailViewModel : ViewModel() {
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>() }

    /**
     * 連線取得個人info
     */
    fun fetchMemberInfo(member: Member) {
        when (member.status) {
            1 -> {
                requestTask<AccountCustomer>(
                    path = "AccountBackstage/1/${member.id}",
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
                    path = "AccountBackstage/2/${member.id}",
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
                    path = "AccountBackstage/3/${member.id}",
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

    /**
     * 連線修改個人資料
     */
    fun editMemberInfo(view: View) {
        user.value?.let {
            if (it.customerId != null) {
                requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                    path = "AccountBackstage",
                    method = "PUT",
                    reqBody = CustomerSharePreferencesUtils.ApiCustomerModel(
                        customerId = it.customerId!!,
                        email = null,
                        name = it.name,
                        phone = it.phone,
                        gender = it.gender,
                        introduction = it.introduction,
                        photo = null,
                        password = null,
                        suspend = it.suspend
                    )
                )?.let {
                    Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
                }
            }
            if (it.cleanerId != null) {
                requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                    path = "AccountBackstage",
                    method = "PUT",
                    reqBody = CleanerSharedPreferencesUtils.ApiCleanerModel(
                        cleanerId = it.cleanerId!!,
                        email = null,
                        name = it.name,
                        phone = it.phone,
                        gender = it.gender,
                        introduction = it.introduction,
                        photo = null,
                        password = null,
                        identifyNumber = it.identifyNumber,
                        verify = it.verify,
                        idCardFront = null,
                        idCardBack = null,
                        crc = null,
                        suspend = it.suspend
                    )
                )?.let {
                    Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
                }
            }
            if (it.backstageId != null) {
                requestTask<BackstageSharedPreferencesUtils.ApiBackstageModel>(
                    path = "AccountBackstage",
                    method = "PUT",
                    reqBody = BackstageSharedPreferencesUtils.ApiBackstageModel(
                        backstageId = it.backstageId!!,
                        account = it.email,
                        name = it.name,
                        password = null,
                        suspend = it.suspend
                    )
                )?.let {
                    Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
                }
            }
        }
    }
}