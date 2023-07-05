package com.example.cleaningapp.backstage.usermanage.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.AccountCleaner
import com.example.cleaningapp.backstage.usermanage.model.Member
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.requestTask

class BsUserVerifyDetailViewModel : ViewModel() {
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>() }

    /** 連線取得個人info */
    fun fetchMemberInfo(member: Member) {
        when (member.status) {
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
        }
    }

    /**
     * 連線修改個人資料
     */
    fun editMemberInfo(view: View) {
        user.value?.let {
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
                        idCardFront = null,
                        idCardBack = null,
                        crc = null,
                        verify = it.verify,
                        suspend = it.suspend
                    )
                )?.let {
                    Navigation.findNavController(view).navigate(R.id.bsUserVerifyFragment)
                }
            }
        }
    }
}