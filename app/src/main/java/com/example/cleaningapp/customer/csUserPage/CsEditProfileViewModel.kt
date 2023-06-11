package com.example.cleaningapp.customer.csUserPage

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.customer.model.Customer
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

class CsEditProfileViewModel : ViewModel() {
    val profile: MutableLiveData<Customer> by lazy { MutableLiveData<Customer>(Customer()) }

    init {
        val preferencesData =
            CustomerSharePreferencesUtils.fetchCustomerInfoFromPreferences<Customer>()
        profile.value = preferencesData
    }

    fun saveMemberInfo(view: View) {
        if (profile.value?.name?.isNotEmpty() == true && profile.value?.phone?.isNotEmpty() == true) {
            val profile = CustomerSharePreferencesUtils.anyToApiCustomerModel(profile.value!!)
            requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
                "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer",
                "PUT",
                profile
            )?.let {
                if (CustomerSharePreferencesUtils.saveCustomerInfoFromPreferences(it)) {
                    Toast.makeText(view.context, "儲存成功", Toast.LENGTH_SHORT).show()
                    Navigation.findNavController(view).popBackStack()
                } else
                    Toast.makeText(view.context, "儲存失敗", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(view.context, "姓名及手機號碼不得空白", Toast.LENGTH_SHORT).show()
        }
    }
}