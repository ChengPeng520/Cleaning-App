package com.example.cleaningapp.login.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.backstage.usermanage.model.AccountBackstage
import com.example.cleaningapp.cleaner.uistate.CleanerMemberInfoUiState
import com.example.cleaningapp.share.CustomerSharePreferencesUtils
import com.google.firebase.messaging.FirebaseMessaging

class LoginViewModel: ViewModel() {
    private val myTag = "TAG_${javaClass.simpleName}"
    val status: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>(listOf("一般會員", "清潔人員", "後台人員")) }
    val account: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val password: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    // 無需使用者操作即可取得token
    init {
        getToken()
    }
    // token在開啟app時即生成(onNewToken)
    // 登入時的按鈕呼叫token, 並將token的值代入查詢字串給後端做update存取在DB
    // 取得token並送到server
    fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { token ->
                    Log.d(myTag, "token: $token")
                }
            }
        }
    }



}

