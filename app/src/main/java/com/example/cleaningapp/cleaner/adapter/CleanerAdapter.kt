package com.example.cleaningapp.cleaner.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.SearchOrder
import com.example.cleaningapp.cleaner.viewmodel.search.CleanerFrontViewModel
import com.example.cleaningapp.databinding.ItemVickyCleanerFrontBinding

class CleanerAdapter(private var cleaners: List<SearchOrder>) :
    RecyclerView.Adapter<CleanerAdapter.CleanerViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun updateCleaners(cleaners: List<SearchOrder>) {
        this.cleaners = cleaners
        notifyDataSetChanged()
    }

    class CleanerViewHolder(val itemViewBinding: ItemVickyCleanerFrontBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return cleaners.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleanerViewHolder {
        val itemViewBinding = ItemVickyCleanerFrontBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = CleanerFrontViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CleanerViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: CleanerViewHolder, position: Int) {
        val cleaner = cleaners[position]

        //position 現在生成位子
        with(holder) {
            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.cleaner?.value = cleaner
            val bundle = Bundle()
            bundle.putInt("orderId", cleaner.orderId)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(
                        R.id.action_cleanerFrontFragment2_to_cleanerFrontOrderDetailFragment,
                        bundle
                    )
            }
        }
    }
}