package com.example.cleaningapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.databinding.ItemVickyCleanerFrontBinding
import java.lang.ref.Cleaner

//class CleanerAdapter(private var cleaners: List<Cleaner>) :
//    RecyclerView.Adapter<CleanerAdapter.CleanerViewHolder>() {
//
//    fun updateCleaners(cleaners: List<Cleaner>) {
//        this.cleaners = cleaners
//        notifyDataSetChanged()
//    }
//    class CleanerViewHolder(val itemViewBinding:) :
//        RecyclerView.ViewHolder(itemViewBinding.root)
//
//    override fun getItemCount(): Int {
//        return cleaners.size
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CleanerViewHolder {
//        val itemViewBinding = ItemVickyCleanerFrontBinding.inflate(
//            LayoutInflater.from(parent.context), parent, false
//        )
//        itemViewBinding.viewModel = CleanerFrontViewModel()
//        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
//        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
//        return CleanerViewHolder(itemViewBinding)
//    }
//    override fun onBindViewHolder(holder: CleanerViewHolder, position: Int) {
//        val cleaner = cleaners[position]
//        with(holder) {
//            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
//            itemViewBinding.viewModel?.friend?.value = cleaner
//            val bundle = Bundle()
//            bundle.putSerializable("cleaner", cleaner)
//            itemView.setOnClickListener {
//                androidx.navigation.Navigation.findNavController(it)
//                    .navigate(R.id.action_cleanerFrontFragment_to_cleanerFrontOrderDetailFragment, bundle)
//            }
//        }
//    }
//}
//}