package com.example.cleaningapp.customer.viewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.detailed.Order

class ComplaintdetailsViewModel : ViewModel() {
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }
    val photoUri: MutableLiveData<Uri> by lazy { MutableLiveData<Uri>() }

    // 這裡假設您有一個名為 capturedCount 的屬性來追蹤已捕獲的照片數量
    var capturedCount: Int = 0
}