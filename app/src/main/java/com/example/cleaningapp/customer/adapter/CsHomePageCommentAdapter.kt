package com.example.cleaningapp.customer.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.customer.csCreateOrder.CsViewCvViewModel
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.databinding.ItemCsPickCleanerBinding

class CsHomePageCommentAdapter (var cleanerList: List<Cleaner>) :
    RecyclerView.Adapter<CsHomePageCommentAdapter.CsHomePageCommentViewHolder>() {
    fun updateCleaners(cleaners: List<Cleaner>) {
        this.cleanerList = cleaners
        notifyDataSetChanged()
    }
    class CsHomePageCommentViewHolder(val itemBinding: ItemCsPickCleanerBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsHomePageCommentViewHolder {
        val itemView =
            ItemCsPickCleanerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemView.viewmodel = CsViewCvViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CsHomePageCommentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cleanerList.size
    }

    override fun onBindViewHolder(holder: CsHomePageCommentViewHolder, position: Int) {
        val cleaner = cleanerList[position]
        with(holder) {
            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemBinding.viewmodel?.cleaner?.value = cleaner
            val bundle = Bundle()
            bundle.putSerializable("cleaner", cleaner)
        }
    }

}