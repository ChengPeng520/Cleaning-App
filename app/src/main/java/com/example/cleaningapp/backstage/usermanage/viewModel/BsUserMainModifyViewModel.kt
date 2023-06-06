package com.example.cleaningapp.backstage.usermanage.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.*
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

class BsUserMainModifyViewModel : ViewModel() {
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>(User()) }

    /**
     * 連線取得個人info
     */
    fun fetchMemberInfo(userFetch: User) {
        if (userFetch.customerId != null && userFetch.cleanerId == null && userFetch.backstageId == null){
                requestTask<AccountCustomer>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/1/${userFetch.customerId}",
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
            }else if (userFetch.customerId == null && userFetch.cleanerId != null && userFetch.backstageId == null){
                requestTask<AccountCleaner>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/2/${userFetch.cleanerId}",
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
            }else if (userFetch.customerId == null && userFetch.cleanerId == null && userFetch.backstageId != null) {
                requestTask<AccountBackstage>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/3/${userFetch.backstageId}",
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

    /**
     * 連線修改個人資料
     */
    fun editMemberInfo(view: View) {
        if (user.value?.customerId == null && user.value?.backstageId == null) {
            val uiState = user.value!!
            requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
                method = "PUT",
                uiState
            )?.let {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
        } else if (user.value?.cleanerId == null && user.value?.backstageId == null) {
            val uistate = CustomerSharePreferencesUtils.anyToApiCustomerModel(user.value!!)
            requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
                method = "PUT",
                uistate
            )?.let {
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
        }

    }

}