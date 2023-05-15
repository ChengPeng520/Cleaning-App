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
import com.example.cleaningapp.backstage.complaint.viewModel.BsCompDetailViewModel
import com.example.cleaningapp.databinding.ItemAlbBsCompDoneDataboxBinding
import com.example.cleaningapp.databinding.ItemAlbBsCompMainDataboxBinding

class ComplaintDoneAdapter(private var complaints: List<Complaint>) :
    RecyclerView.Adapter<ComplaintDoneAdapter.ComplaintDoneViewHolder>()  {

    /**
     * 更新使用者列表內容
     * @param complaints 新的好友列表
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateComplaints(complaints: List<Complaint>) {
        this.complaints = complaints
        notifyDataSetChanged()
    }

    class ComplaintDoneViewHolder(val itemViewBinding: ItemAlbBsCompDoneDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return complaints.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintDoneViewHolder {
        val itemViewBinding = ItemAlbBsCompDoneDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsCompDetailViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ComplaintDoneViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ComplaintDoneViewHolder, position: Int) {
        val complaint = complaints[position]
        with(holder) {
            // 將欲顯示的friend物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.complaint?.value = complaint
            val bundle = Bundle()
            bundle.putSerializable("complaint", complaint)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_bsCompDoneFragment_to_bsCompDetailFragment, bundle)
            }
        }
    }
}