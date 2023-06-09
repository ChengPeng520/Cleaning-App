package com.example.cleaningapp.backstage.usermanage.controller

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.backstage.usermanage.model.Member
import com.example.cleaningapp.backstage.usermanage.viewModel.BsUserSuspendViewModel
import com.example.cleaningapp.databinding.ItemAlbBsUserSuspDataboxBinding

/**
 * 使用者列表所需的Adapter
 */
class UserSuspendAdapter(private var users: List<Member>) :
    RecyclerView.Adapter<UserSuspendAdapter.UserSuspendViewHolder>() {
    /**
     * 更新使用者列表內容
     * @param users 新的好友列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateUsers(users: List<Member>) {
        this.users = users
        notifyDataSetChanged()
    }

    class UserSuspendViewHolder(val itemViewBinding: ItemAlbBsUserSuspDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(member: Member) {
            //將數據綁定在view
            itemViewBinding.btnOpenAccount.setOnClickListener {
                showDialogOpen(member)
            }
        }

        private fun showDialogOpen(member: Member) {
            val alertDialogBuilder =
                AlertDialog.Builder(itemViewBinding.root.context)  //dialog建立的畫面在binding itemView的內容裡
            alertDialogBuilder.setTitle("確定復原此帳號？")
            alertDialogBuilder.setMessage("將發送訊息給使用者")
            alertDialogBuilder.setPositiveButton("確定") { dialog, _ ->
                //TODO 沒有附到值
                itemViewBinding.viewModel?.member?.value?.suspend = false
                itemViewBinding.viewModel?.editMemberInfo(itemView)
                dialog.dismiss()
            }
            alertDialogBuilder.setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
            alertDialogBuilder.show()
        }
    }


    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserSuspendViewHolder {
        val itemViewBinding = ItemAlbBsUserSuspDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsUserSuspendViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return UserSuspendViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: UserSuspendViewHolder, position: Int) {
        val member = users[position]
        holder.itemViewBinding.viewModel?.member?.value = member
        holder.bind(member)
    }
}