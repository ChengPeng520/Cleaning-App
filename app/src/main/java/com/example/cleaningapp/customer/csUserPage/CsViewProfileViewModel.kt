package com.example.cleaningapp.customer.csUserPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Customer
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.example.cleaningapp.share.requestTask

class CsViewProfileViewModel : ViewModel() {
    val profile : MutableLiveData<Customer> by lazy { MutableLiveData<Customer>(Customer()) }

    init {
        val result = requestTask<CustomerSharePreferencesUtils.ApiCustomerModel>(
            "http://10.0.2.2:8080/javaweb-cleaningapp/AccountCustomer/kk0128k@gmail.com/123456"
        )
        if (result != null) {
            CustomerSharePreferencesUtils.saveCustomerInfoFromPreferences(result)
            profile.value =
                CustomerSharePreferencesUtils.fetchCustomerInfoFromPreferences<Customer>()
        }
    }
}