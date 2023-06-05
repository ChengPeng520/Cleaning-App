package com.example.cleaningapp.backstage.complaint.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.backstage.complaint.model.Complaint
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDealingViewModel
import com.example.cleaningapp.databinding.ItemAlbBsCompMainDataboxBinding

class ComplaintAdapter(private var complaints: List<Complaint>) :
    RecyclerView.Adapter<ComplaintAdapter.ComplaintViewHolder>() {

    /**
     * 更新使用者列表內容
     * @param complaints 新的好友列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateComplaints(complaints: List<Complaint>) {
        this.complaints = complaints
        notifyDataSetChanged()
    }

    class ComplaintViewHolder(val itemViewBinding: ItemAlbBsCompMainDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return complaints.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val itemViewBinding = ItemAlbBsCompMainDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsCompDealingViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ComplaintViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {
        val complaint = complaints[position]
        with(holder) {
            // 將欲顯示的complaint物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.complaint?.value = complaint
            val bundle = Bundle()
            bundle.putSerializable("complaint", complaint)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_bsCompFragment_to_bsCompDealingFragment, bundle)
            }
        }
    }
}