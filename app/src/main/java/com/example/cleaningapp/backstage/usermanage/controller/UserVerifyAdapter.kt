package com.example.cleaningapp.backstage.usermanage.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.User
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserVerifyDetailViewModel
import com.example.cleaningapp.databinding.ItemAlbBsUserVerifyDataboxBinding

/**
 * 使用者列表所需的Adapter
 */
class UserVerifyAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UserVerifyAdapter.UserVerifyViewHolder>() {

    /**
     * 更新使用者列表內容
     * @param users 新的好友列表
     */
    fun updateUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserVerifyViewHolder(val itemViewBinding: ItemAlbBsUserVerifyDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVerifyViewHolder {
        val itemViewBinding = ItemAlbBsUserVerifyDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsUserVerifyDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return UserVerifyViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: UserVerifyViewHolder, position: Int) {
        val user = users[position]
        with(holder) {
            // 將欲顯示的friend物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.user?.value = user
            val bundle = Bundle()
            bundle.putSerializable("user", user)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_bsUserVerifyFragment_to_bsUserVerifyDetailFragment, bundle)
            }
        }
    }
}