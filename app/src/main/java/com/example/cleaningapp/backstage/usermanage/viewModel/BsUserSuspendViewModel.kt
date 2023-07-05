package com.example.cleaningapp.backstage.usermanage.viewModel

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Member
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.share.requestTask
import com.google.gson.reflect.TypeToken

class BsUserSuspendViewModel : ViewModel() {
    data class Customer(
        val customerId: Int,
        val suspend: Boolean
    )

    data class Cleaner(
        val cleanerId: Int,
        val suspend: Boolean
    )

    data class Backstage(
        val backstageId: Int,
        val suspend: Boolean
    )

    //原始使用者列表
    private var userList = listOf<Member>()

    // 受監控的LiveData，一旦指派新值就會更新使用者列表畫面
    val users: MutableLiveData<List<Member>> by lazy { MutableLiveData<List<Member>>() }
    val user: MutableLiveData<User> by lazy { MutableLiveData<User>() }
    val member: MutableLiveData<Member> by lazy { MutableLiveData<Member>() }

    init {
        loadUsers()
    }

    /** 連線後端取得資料 */
    private fun loadUsers() {
        requestTask<List<Member>>(
            "AccountBackstage/",
            respBodyType = object : TypeToken<List<Member>>() {}.type
        )?.let {
            val suspendUsers = mutableListOf<Member>()
            for (a in it) {
                if (a.suspend) {
                    suspendUsers.add(a)
                }
            }
            users.value = suspendUsers
            userList = suspendUsers
        }
    }

    /**
     * 連線修改個人資料
     */
    fun editMemberInfo(view: View) {
        member.value?.let {
            val member: Any =
                when (it.status) {
                    1 -> {
                        Customer(it.id, it.suspend)
                    }
                    2 -> {
                        Cleaner(it.id, it.suspend)
                    }
                    3 -> {
                        Backstage(it.id, it.suspend)
                    }
                    else -> {}
                }
            requestTask<User>(
                path = "AccountBackstage",
                method = "PUT",
                reqBody = member
            )?.let {
                Toast.makeText(view.context, "帳號已開通", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
            }
//            when (it.status) {
//                1 -> {
//                    requestTask<Member>(
//                        url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
//                        method = "PUT",
//                        reqBody = member
//                    )?.let {
//                        Toast.makeText(view.context, "帳號已開通", Toast.LENGTH_SHORT).show()
//                        Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
//                    }
//                }
//                2 -> {
//                    requestTask<CleanerSharedPreferencesUtils.ApiCleanerModel>(
//                        url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
//                        method = "PUT",
//                        reqBody = member
//                        )
//                    )?.let {
//                        Toast.makeText(view.context, "帳號已開通", Toast.LENGTH_SHORT).show()
//                        Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
//                    }
//                }
//                3 -> {
//                    requestTask<BackstageSharedPreferencesUtils.ApiBackstageModel>(
//                        url = "http://10.0.2.2:8080/javaweb-cleaningapp/AccountBackstage",
//                        method = "PUT",
//                        reqBody = BackstageSharedPreferencesUtils.ApiBackstageModel(
//                            backstageId = it.id,
//                            account = it.email,
//                            name = it.name,
//                            password = null,
//                            suspend = it.suspend
//                        )
//                    )?.let {
//                        Toast.makeText(view.context, "帳號已開通", Toast.LENGTH_SHORT).show()
//                        Navigation.findNavController(view).navigate(R.id.bsUserSuspendFragment)
//                    }
//                }
//                else -> {
//                    Toast.makeText(view.context, "你484搞錯甚麼了", Toast.LENGTH_LONG).show()
//                }
//            }
        }
    }

    /**
     * 如果搜尋條件為空字串，就顯示原始使用者列表；否則就顯示搜尋後結果
     * @param newText 欲搜尋的條件字串
     */
    fun search(newText: String?) {
        if (newText == null || newText.isEmpty()) {
            users.value = userList
        } else {
            val searchUserList = mutableListOf<Member>()
            users.value?.forEach { user ->
                if (user.name.contains(newText, true) ||
                    user.email.contains(newText, true)
                ) {
                    searchUserList.add(user)
                }
            }
            users.value = searchUserList
        }
    }

    /** 模擬取得遠端資料 */
//    private fun loadUsersTest() {
//        val userList = mutableListOf<User>()
//        userList.add(
//            User(
//                0,
//                R.drawable.alb_account_avatar, "rona87@gmail.com", "P@ssw0rd", "Rona",
//                "0987487487", "高雄市南投區中山路12號8樓", "女",
//                "2023-05-09 02:13", "2023-05-09 09:13", "一般用戶", "開通",
//                "開通",
//                "我是一名熱愛閱讀和旅行的年輕人，喜歡在不同的地方體驗當地文化和風俗習慣，同時也享受與書本相伴的寧靜時光。",
//                "N223456789",
//                "1234432157785",
//                R.drawable.alb_idcard_front,
//                R.drawable.alb_idcard_back,
//                R.drawable.alb_criminal_record
//            )
//        )
//        userList.add(
//            User(
//                1,
//                R.drawable.alb_account_avatar, "ally87@gmail.com", "password", "Ally",
//                "0987987987", "台北市中山區中山路12號8樓", "女", "2023-05-09 02:20",
//                "2023-05-09 09:13", "清潔人員", "開通",
//                "開通",
//                "我是一位熱衷於技術創新的工程師，擅長將複雜的問題簡化成易於理解和實現的方案，並善於團隊合作實現目標。",
//                "A298384939",
//                "1938275473829",
//                R.drawable.alb_idcard_front,
//                R.drawable.alb_idcard_back,
//                R.drawable.alb_criminal_record
//            )
//        )
//        userList.add(
//            User(
//                2,
//                R.drawable.alb_account_avatar, "ciyi87@gmail.com", "PASSWORD", "Ciyi",
//                "0987087087", "台中市豐原區中山路12號8樓", "女", "2023-05-09 02:30",
//                "2023-05-09 09:13", "一般用戶", "開通",
//                "開通",
//                "我是一名喜愛運動和音樂的青年，平時喜歡打籃球、游泳和跑步，同時也熱愛彈奏吉他和創作音樂，享受在運動和音樂中放鬆身心。",
//                "F239483721",
//                "9371635234958",
//                R.drawable.alb_idcard_front,
//                R.drawable.alb_idcard_back,
//                R.drawable.alb_criminal_record
//            )
//        )
//        this.userList = userList
//        this.users.value = this.userList
//    }
}