package com.example.cleaningapp.customer.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.customer.model.Comment
import com.example.cleaningapp.databinding.ItemCsViewCommentBinding

class CsCommentAdapter (var comments: List<Comment>) :
    RecyclerView.Adapter<CsCommentAdapter.CsCommentViewHolder>() {

    fun updateComments(comments: List<Comment>) {
        this.comments = comments
        notifyDataSetChanged()
    }

    class CsCommentViewHolder(val itemBinding: ItemCsViewCommentBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsCommentViewHolder {
        val itemView =
            ItemCsViewCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemView.viewModel = CsCommentViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CsCommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: CsCommentViewHolder, position: Int) {
        val comment = comments[position]
        with(holder) {
            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemBinding.viewModel?.commentItem?.value = comment
            val bundle = Bundle()
            bundle.putSerializable("comment", comment)
        }
    }

}