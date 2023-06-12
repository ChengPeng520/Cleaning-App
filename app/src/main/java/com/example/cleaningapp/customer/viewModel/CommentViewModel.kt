// CommentViewModel.kt

package com.example.cleaningapp.customer.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.detailed.Order
import com.example.cleaningapp.customer.detailed.OrderInfo
import com.example.cleaningapp.share.requestTask

class CommentViewModel : ViewModel() {
    val stars: MutableLiveData<Float> by lazy { MutableLiveData<Float>(0F) }
    val content: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val orderId: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    val order: MutableLiveData<Order> by lazy { MutableLiveData<Order>() }

    fun uploadContent(view: View) {
        requestTask<OrderInfo>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/csOrder/",
            method = "PUT",
            reqBody = OrderInfo(
                Order(
                    stars = stars.value!!,
                    commentCleaner = content.value!!,
                    status = 8
                ), emptyList()
            )
        )?.let {
            view.findNavController().navigate(R.id.action_commentFragment_to_commentDoneFragment)
        }
    }
}