package com.example.cleaningapp.backstage.usermanage.viewModel

import android.database.Observable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.AccountBackstage
import com.example.cleaningapp.backstage.usermanage.model.AccountCleaner
import com.example.cleaningapp.backstage.usermanage.model.AccountCustomer
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.share.BackstageSharedPreferencesUtils
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

class BsUserMainModifyViewModel : ViewModel() {
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>(User()) }

    /**
     * 連線取得個人info
     */
    fun fetchMemberInfo(userFetch: User) {
        if (userFetch.customerId != null) {
            Log.d("1", "1")
            requestTask<AccountCustomer>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/1/${userFetch.customerId}",
                method = "GET"
            )?.let {
                user.value = User(
                    customerId = it.customerId,
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
                    identifyNumber = ""
                )
            }
        } else if (userFetch.cleanerId != null) {
            Log.d("2", "2")
            requestTask<AccountCleaner>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/2/${userFetch.cleanerId}",
                method = "GET"
            )?.let {
                user.value = User(
                    cleanerId = it.cleanerId,
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
        } else if (userFetch.backstageId != null) {
            Log.d("3", "3")
            requestTask<AccountBackstage>(
                url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage/3/${userFetch.backstageId}",
                method = "GET"
            )?.let {
                user.value = User(
                    backstageId = it.backstageId,
                    email = it.account,
                    name = it.name,
                    gender = 0,
                    timeCreate = it.timeCreate,
                    timeUpdate = it.timeUpdate,
                    role = "後台人員",
                    suspend = it.suspend,
                    verify = false,
                )
            }
        }
    }

    /**
     * 連線修改個人資料
     */
    fun editMemberInfo(view: View): Boolean {
        user.value?.let {
            if (it.customerId != null) {
                requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
                    method = "PUT",
                    reqBody = CustomerSharePreferencesUtils.ApiCustomerModel(
                        customerId = it.customerId!!,
                        email = null,
                        name = it.name,
                        phone = it.phone,
                        gender = it.gender,
                        introduction = it.introduction,
                        photo = null,
                        suspend = it.suspend,
                        password = null
                    )
                )?.let {
                    return true
                }
                return false
            }
            if (it.cleanerId != null) {
                requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
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
                        suspend = it.suspend,
                        idCardBack = null,
                        crc = null
                    )
                )?.let {
                    Navigation.findNavController(view).popBackStack()
                    return true
                }
                return false
            }
            if (it.backstageId != null) {
                requestTask<BackstageSharedPreferencesUtils.ApiBackstageModel>(
                    url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
                    method = "PUT",
                    reqBody = BackstageSharedPreferencesUtils.ApiBackstageModel(
                        backstageId = it.backstageId!!,
                        account = it.email,
                        name = it.name,
                        password = null,
                        suspend = it.suspend
                    )
                )?.let {
                    Navigation.findNavController(view).popBackStack()
                    return true
                }
                return false
            }
        }
        return false
    }
}