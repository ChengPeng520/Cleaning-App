package com.example.cleaningapp.customer.recyclerView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Comment


class CsCommentViewModel : ViewModel() {
    val commentItem: MutableLiveData<Comment> by lazy { MutableLiveData<Comment>() }

}