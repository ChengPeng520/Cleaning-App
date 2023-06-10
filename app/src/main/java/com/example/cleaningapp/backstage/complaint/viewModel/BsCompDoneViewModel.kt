package com.example.cleaningapp.backstage.complaint.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.model.BSCompOrderItem
import com.example.cleaningapp.backstage.complaint.model.Complaint
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsCompDoneViewModel : ViewModel() {
    private var complaintList = listOf<BSCompOrderItem>()

    // 受監控的LiveData，一旦指派新值就會更新使用者列表畫面
    val complaints: MutableLiveData<List<BSCompOrderItem>> by lazy { MutableLiveData<List<BSCompOrderItem>>() }
    val complaint: MutableLiveData<BSCompOrderItem> by lazy { MutableLiveData<BSCompOrderItem>() }

    init {
        loadComplaints()
    }

    private fun loadComplaints() {
        requestTask<List<BSCompOrderItem>>(
            url = "http://10.0.2.2:8080/javaweb-cleaningapp/bsOrder/compOrders7/",
            method = "GET",
            respBodyType = object : TypeToken<List<BSCompOrderItem>>() {}.type
        )?.let {
            complaints.value = it
            complaintList = it
        }
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始使用者列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            complaints.value = complaintList
        } else {
            val searchComplaintList = mutableListOf<BSCompOrderItem>()
            complaintList.forEach { complaint ->
                if (complaint.customerName.contains(newText, true) ||
                    complaint.timeCreate.toString().contains(newText, true)
                ) {
                    searchComplaintList.add(complaint)
                }
            }
            complaints.value = searchComplaintList
        }
    }

    /** 模擬取得遠端資料 */
//    private fun loadComplaints() {
//        val complaintList = mutableListOf<Complaint>()
//        complaintList.add(
//            Complaint(
//                0,
//                "1",
//                "Rona",
//                "2023-05-09 09:13",
//                "Ally",
//                "已同意",
//                "2023-05-09 02:13",
//                R.drawable.alb_account_avatar,
//                R.drawable.alb_account_avatar,
//                R.drawable.alb_account_avatar,
//                "還不過年還有三週。就趁機漲了7.5倍價，超坑人",
//                "今年因為疫情的關係師傅工資一個月半前就漲到不行，我們也很無奈"
//            )
//        )
//        complaintList.add(
//            Complaint(
//                1,
//                "2",
//                "Ciyi",
//                "2023-05-09 09:13",
//                "Victor",
//                "已同意",
//                "2023-05-09 02:13",
//                R.drawable.alb_account_avatar,
//                R.drawable.alb_account_avatar,
//                R.drawable.alb_account_avatar,
//                "收費亂開價",
//                "謝謝您的反應，很抱歉帶給您不好的服務體驗，關於服務的品質一直都是我們最在意的"
//            )
//        )
//        complaintList.add(
//            Complaint(
//                2,
//                "3",
//                "Vicky",
//                "2023-05-09 09:13",
//                "Albert",
//                "已拒絕",
//                "2023-05-09 02:13",
//                R.drawable.alb_account_avatar,
//                R.drawable.alb_account_avatar,
//                R.drawable.alb_account_avatar,
//                "感受很差，收費比別人高，卻沒有比別人清潔的完整！！！！完全不推薦！",
//                "很抱歉讓您滿心期待的服務有落差，在第一次預約服務時，建議您可先就重點部位處理，您若滿意可再陸續預約加強人力"
//            )
//        )
//
//        this.complaintList = complaintList
//        this.complaints.value = this.complaintList
//    }
}