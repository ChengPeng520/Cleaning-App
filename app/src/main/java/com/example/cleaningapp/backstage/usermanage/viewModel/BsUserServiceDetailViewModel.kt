package com.example.cleaningapp.backstage.usermanage.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.User

class BsUserServiceDetailViewModel {
    val chat: MutableLiveData<Chat> by lazy { MutableLiveData<Chat>() }
}