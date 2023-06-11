package com.example.cleaningapp.share

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseFCMService : FirebaseMessagingService() {
    private val myTag = "TAG_${javaClass.simpleName}"

    // 當App在前景收到FCM時呼叫，App在背景收到FCM時不會呼叫此方法
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        var title = ""
        var body = ""
        // 取得notification資料，主要為title與body這2個保留字
        remoteMessage.notification?.let { notification ->
            title = notification.title ?: ""
            body = notification.body ?: ""
        }
        // 取得自訂資料
        remoteMessage.data["chatroom"]?.let {
            if (it == "chatroom") {
                sendMessageBroadcast(it)
            }
            if (it == "order") {
                sendOrderBroadcast(it)
            }
            Log.d(
                myTag,
                "onMessageReceived():\ntitle: $title, body: $body, data: $it"
            )
        }
    }

    // 當registration token更新時呼叫，應該將新的token傳送至server
    override fun onNewToken(token: String) {
        Log.d(myTag, "onNewToken: $token")
//        MainVM().sendTokenToServer(token)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
        Log.d(myTag, "onDeletedMessages")
    }

    private fun sendMessageBroadcast(data: String) {
        val intent = Intent("action_chatroom") //要執行的id(自訂義的)標籤, 後在IntentFilter配合使用
        val bundle = Bundle()
        bundle.putString("data", data)
        intent.putExtras(bundle)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    private fun sendOrderBroadcast(data: String) {
        val intent = Intent("action_order") //要執行的id(自訂義的)標籤, 後在IntentFilter配合使用
        val bundle = Bundle()
        bundle.putString("data", data)
        intent.putExtras(bundle)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }
}