package com.example.cleaningapp.customer.csUserPage

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.CleanerMemberInfoUiState
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Coupon
import com.example.cleaningapp.customer.model.Customer
import com.example.cleaningapp.share.CleanerSharedPreferencesUtils
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

class CsEditProfileViewModel : ViewModel() {
    val profile : MutableLiveData<Customer> by lazy { MutableLiveData<Customer>(Customer()) }

    init {
        val preferencesData = CustomerSharePreferencesUtils.fetchCustomerInfoFromPreferences<Customer>()
        profile.value = preferencesData
    }

    fun saveMemberInfo(view: View) {
        if (profile.value?.name?.isNotEmpty() == true && profile.value?.phone?.isNotEmpty() == true) {
            val profile = CustomerSharePreferencesUtils.anyToApiCustomerModel(profile.value!!)
            val result = requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer",
                "PUT",
                profile
            )
            if (result != null) {
                if (CustomerSharePreferencesUtils.saveCustomerInfoFromPreferences(result)) {
                    Toast.makeText(view.context, "儲存成功", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack()
                }
                else
                    Toast.makeText(view.context, "儲存失敗", Toast.LENGTH_SHORT).show()
            }
        }
    }


}


