package com.example.cleaningapp.customer.csCreateOrder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Comment

class CsChooseCleanerViewModel : ViewModel() {
    //受監控的LiveDATA，一旦指派新值就會更新使用者列表畫面
    val cleanerList: MutableLiveData<List<Cleaner>> by lazy { MutableLiveData<List<Cleaner>>() }
    val commentItem: MutableLiveData<Comment> by lazy { MutableLiveData<Comment>() }
    //  原始使用者列表
    private var cleaners = mutableListOf<Cleaner>()

    init {
        fetchCleaners()
    }

    fun fetchCleaners() {
        val list = mutableListOf<Cleaner>()
        list.add(Cleaner(1, R.drawable.cs_cleaner_img1,"曾正凡", true,2.1f, 1,"0911111111","台北市中正區重慶南路一段122號","我叫曾正凡，是補教業的名師，藝名是呂凡老師。我不教國中生，因為年紀太小了，我不喜歡被說老牛吃嫩草，被我教過的學生英文都考一百分。"))
        list.add(Cleaner(2, R.drawable.cs_cleaner_img2,"呂正芃", true,3.5f, 3,"0922222222","台北市中正區中山南路1號","我叫呂正芃，多益875分，可以跟外籍雇主溝通，但是多語化服務要加錢。"))
        list.add(Cleaner(3, R.drawable.cs_img_xxx,"王睿睿", true,4.3f, 5,"0933333333","新北市三重區重新路三段145號","我叫王睿睿，住在新北市三重區，因為打架打不贏班上叼著菸的半甲哥，所以下課時間都去公園搶小朋友的溜滑梯。有一天一個小妹妹說我是穿著白衣服的壞男生，後來就再也沒人見過這個小妹妹，你問我他去哪裡了？我他媽怎麼會知道。"))
        list.add(Cleaner(4, R.drawable.cs_cleaner_img4,"掰頂姊", false,5.0f, 7,"0944444444","台北市中山區南京東路三段219號5樓","我叫掰頂姊，喜歡在水裡游來游去，有一天被姜太公釣上岸，這裡什麼東西都要錢，為了生存我成為了提拔女孩。"))

        this.cleaners = list
        this.cleanerList.value = this.cleaners
    }
}