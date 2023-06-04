package com.example.cleaningapp.customer.csUserPage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner

class CsUserPageViewModel : ViewModel() {
    val cleaner : MutableLiveData<Cleaner> by lazy { MutableLiveData<Cleaner>() }
//    var profile = Cleaner(3, R.drawable.cs_img_xxx,"王睿睿", true,4.3f, 5,"0933333333","新北市三重區重新路三段145號","我叫王睿睿，住在新北市三重區，因為打架打不贏班上叼著菸的半甲哥，所以下課時間都去公園搶小朋友的溜滑梯。有一天一個小妹妹說我是穿著白衣服的壞男生，後來就再也沒人見過這個小妹妹，你問我他去哪裡了？我他媽怎麼會知道。")
}