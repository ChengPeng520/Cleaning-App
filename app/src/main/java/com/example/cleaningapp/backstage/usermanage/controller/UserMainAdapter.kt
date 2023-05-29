package com.example.cleaningapp.backstage.usermanage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserMainDetailViewModel
import com.example.cleaningapp.databinding.ItemAlbBsUserMainDataboxBinding

/**
 * 使用者列表所需的Adapter
 */
class UserMainAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserMainAdapter.UserMainViewHolder>() {



    /**
     * 更新使用者列表內容
     * @param users 新的好友列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserMainViewHolder(val itemViewBinding: ItemAlbBsUserMainDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        //根據 isEmpty 的值資料集是否為空
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserMainViewHolder {
        val itemViewBinding = ItemAlbBsUserMainDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsUserMainDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return UserMainViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: UserMainViewHolder, position: Int) {
        val user = users[position]
        with(holder) {
            // 將欲顯示的friend物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.user?.value = user
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_bsAccountManageMainFragment_to_bsAccountManageMainDetailFragment, bundle)
            }
        }
    }
}