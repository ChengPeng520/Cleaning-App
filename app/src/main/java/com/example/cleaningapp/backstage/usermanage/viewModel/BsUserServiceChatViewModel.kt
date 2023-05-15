package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.User


class BsUserServiceChatViewModel : ViewModel() {
    val chat: MutableLiveData<Chat> by lazy { MutableLiveData<Chat>() }
}