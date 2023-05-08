package com.example.cleaningapp.cleaner.adapter.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductItemUiState
import com.example.cleaningapp.cleaner.viewmodel.shop.ShopViewModel
import com.example.cleaningapp.databinding.ItemFatrueiShopProductBinding

class ShopAdapter : ListAdapter<ProductItemUiState, ShopAdapter.MyViewHodler>(DiffCallBack()) {
    class MyViewHodler(val binding: ItemFatrueiShopProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        val itemViewBinding = ItemFatrueiShopProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemViewBinding.viewModel = ShopViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return MyViewHodler(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        holder.binding.viewModel?.itemUiState?.value = getItem(position)
        holder.binding.ivShopProductPicture.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", getItem(position).id)
            Navigation.findNavController(it)
                .navigate(R.id.action_shopFragment_to_productDetailFragment, bundle)
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<ProductItemUiState>() {
    override fun areItemsTheSame(
        oldItem: ProductItemUiState,
        newItem: ProductItemUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ProductItemUiState,
        newItem: ProductItemUiState
    ): Boolean {
        return oldItem == newItem
    }
}