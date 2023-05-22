package com.example.cleaningapp.customer.csHomePage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleaningapp.customer.model.Coupon

class CsCouponObtainViewModel : ViewModel() {
    // 原始好友列表
    private var couponList = mutableListOf<Coupon>()
    val coupon: MutableLiveData<Coupon> by lazy { MutableLiveData<Coupon>() }

    // 受監控的LiveData，一旦指派新值就會更新好友列表畫面
    val coupons: MutableLiveData<List<Coupon>> by lazy { MutableLiveData<List<Coupon>>() }

    init {
        loadCoupons()
    }

//    /**
//     * 如果搜尋條件為空字串，就顯示原始好友列表；否則就顯示搜尋後結果
//     * @param newText 欲搜尋的條件字串
//     */
//    fun search(newText: String?) {
//        if (newText == null || newText.isEmpty()) {
//            friends.value = friendList
//        } else {
//            val searchFriendList = mutableListOf<Friend>()
//            friendList.forEach { friend ->
//                if (friend.name.contains(newText, true)) {
//                    searchFriendList.add(friend)
//                }
//            }
//            friends.value = searchFriendList
//        }
//    }

    /** 模擬取得遠端資料 */
    private fun loadCoupons() {
        val couponList = mutableListOf<Coupon>()
        couponList.add(Coupon(1, false, 0.80, 500, 300, "2023/5/31", true))
        couponList.add(Coupon(2, false, 0.90, 300, 500, "2023/5/31", true))
        couponList.add(Coupon(3, true, 100.00, 800, 500, "2023/6/30", false))
        this.couponList = couponList
        this.coupons.value = this.couponList
    }
}