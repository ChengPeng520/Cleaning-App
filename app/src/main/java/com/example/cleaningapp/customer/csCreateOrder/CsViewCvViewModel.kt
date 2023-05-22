package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Comment


class CsViewCvViewModel :ViewModel() {
    //  原始使用者列表
    private var commentList = mutableListOf<Comment>()
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val comments: MutableLiveData<List<Comment>> by lazy { MutableLiveData<List<Comment>>() }
    val cleaner: MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }
    init {
        updateComments()
    }

    private fun updateComments() {
        val commentList = mutableListOf<Comment>()
        commentList.add(Comment(1, "呂凡老師", R.drawable.cs_cleaner_img1,111,2,5.0f,"好棒棒，亮晶晶"))
        commentList.add(Comment(2, "王睿睿",R.drawable.cs_cleaner_img2,112,2,4.9f,"樓上教我英文"))
        commentList.add(Comment(3, "隔壁老王",R.drawable.cs_cleaner_img4,114,2,0.5f,"小三的頭髮留在地板上沒掃到"))
        commentList.add(Comment(4, "掰頂姊",R.drawable.cs_cleaner_img3,113,2,4.8f,"桌上少了10塊錢"))
        this.commentList = commentList
        this.comments.value = this.commentList
    }

}