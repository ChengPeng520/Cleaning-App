package com.example.cleaningapp.backstage.usermanage.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.usermanage.model.Chat
import com.example.cleaningapp.backstage.usermanage.model.ChatItem
import com.example.cleaningapp.backstage.usermanage.model.Chatroom
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserServiceDetailViewModel
import com.example.cleaningapp.cleaner.uistate.ChatroomItemUiState
import com.example.cleaningapp.databinding.ItemAlbBsUserServChatTxtBinding
import com.example.cleaningapp.databinding.ItemAlbBsUserServDataboxBinding
import com.example.cleaningapp.databinding.ItemFatrueiChatroomTxtBinding

/**
 * 聊天室列表所需的Adapter
 */
class UserServiceAdapter(private var chats: List<Chatroom>) :
    RecyclerView.Adapter<UserServiceAdapter.UserServiceViewHolder>() {

    /**
     * 更新聊天室列表內容
     * @param chats 聊天室列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateChats(chats: List<Chatroom>) {
        this.chats = chats
        notifyDataSetChanged()
    }

    class UserServiceViewHolder(val itemViewBinding: ItemAlbBsUserServDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserServiceViewHolder {
        val itemViewBinding = ItemAlbBsUserServDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsUserServiceDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return UserServiceViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: UserServiceViewHolder, position: Int) {
        val chat = chats[position]
        with(holder) {
            // 將欲顯示的friend物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.chat?.value = chat
            val bundle = Bundle()
            bundle.putSerializable("chat", chat)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_bsUserServiceFragment_to_bsUserServiceChatFragment,
                        bundle
                    )
            }
        }
    }
}