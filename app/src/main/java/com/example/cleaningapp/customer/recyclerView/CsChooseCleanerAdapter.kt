package com.example.cleaningapp.customer.recyclerView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.databinding.ItemCsPickCleanerBinding

class CsChooseCleanerAdapter(var cleanerList: List<Cleaner>) :
    RecyclerView.Adapter<CsChooseCleanerAdapter.CsChooseCleanerViewHolder>() {

    fun updateCleaners(cleaners: List<Cleaner>) {
        this.cleanerList = cleaners
        notifyDataSetChanged()
    }

    class CsChooseCleanerViewHolder(val itemBinding: ItemCsPickCleanerBinding):
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsChooseCleanerViewHolder {
        val itemView = ItemCsPickCleanerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemView.viewmodel = CsCleanerViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CsChooseCleanerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cleanerList.size
    }

    override fun onBindViewHolder(holder: CsChooseCleanerViewHolder, position: Int) {
        val cleaner = cleanerList[position]
        with(holder) {
            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemBinding.viewmodel?.cleanerItem?.value = cleaner
            val bundle = Bundle()
            bundle.putSerializable("cleaner", cleaner)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_csChooseCleanerFragment2_to_csViewCvFragment, bundle)
            }
        }
    }

}