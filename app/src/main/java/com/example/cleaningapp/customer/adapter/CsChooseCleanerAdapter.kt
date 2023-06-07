package com.example.cleaningapp.customer.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.customer.csCreateOrder.CsChooseCleanerViewModel
import com.example.cleaningapp.customer.csCreateOrder.CsViewCvViewModel
import com.example.cleaningapp.customer.model.Cleaner
import com.example.cleaningapp.customer.model.Order
import com.example.cleaningapp.customer.model.OrderEstablished
import com.example.cleaningapp.databinding.ItemCsPickCleanerBinding

class CsChooseCleanerAdapter(var cleaners: List<Cleaner>, var orderId: Int) :
    RecyclerView.Adapter<CsChooseCleanerAdapter.CsChooseCleanerViewHolder>() {

    fun updateCleaners(cleaners: List<Cleaner>) {
        this.cleaners = cleaners
        notifyDataSetChanged()
    }

    class CsChooseCleanerViewHolder(val itemBinding: ItemCsPickCleanerBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CsChooseCleanerViewHolder {
        val itemView = ItemCsPickCleanerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val ccViewModel = CsChooseCleanerViewModel()
        itemView.viewmodel = CsViewCvViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return CsChooseCleanerViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cleaners.size
    }

    override fun onBindViewHolder(holder: CsChooseCleanerViewHolder, position: Int) {
        val cleaner = cleaners[position]
        with(holder) {
            // 將欲顯示的cleaner物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemBinding.viewmodel?.cleaner?.value = cleaner
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("cleanerId", cleaner.cleanerId)
                bundle.putInt("orderId", orderId)
                Log.d("orderId","orderID: $orderId")
                Navigation.findNavController(it)
                    .navigate(R.id.action_csChooseCleanerFragment2_to_csViewCvFragment, bundle)
            }
        }
    }
}