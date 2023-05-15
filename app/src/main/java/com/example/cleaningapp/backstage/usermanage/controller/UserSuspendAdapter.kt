package com.example.cleaningapp.backstage.usermanage.controller

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserSuspendDetailViewModel
import com.example.cleaningapp.databinding.ItemAlbBsUserSuspDataboxBinding

/**
 * 使用者列表所需的Adapter
 */
class UserSuspendAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserSuspendAdapter.UserSuspendViewHolder>() {

    /**
     * 更新使用者列表內容
     * @param users 新的好友列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserSuspendViewHolder(val itemViewBinding: ItemAlbBsUserSuspDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSuspendViewHolder {
        val itemViewBinding = ItemAlbBsUserSuspDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsUserSuspendDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return UserSuspendViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: UserSuspendViewHolder, position: Int) {
        holder.itemViewBinding.viewModel?.user?.value = users[position]
    }
}