package com.example.cleaningapp.cleaner.view.shop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductsItemUiState
import com.example.cleaningapp.cleaner.viewmodel.shop.ShopViewModel
import com.example.cleaningapp.databinding.ItemFatrueiShopProductBinding

class ShopAdapter : ListAdapter<ProductsItemUiState, ShopAdapter.MyViewHodler>(DiffCallBack()) {

    class DiffCallBack : DiffUtil.ItemCallback<ProductsItemUiState>() {
        override fun areItemsTheSame(
            oldItem: ProductsItemUiState,
            newItem: ProductsItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductsItemUiState,
            newItem: ProductsItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

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
            Navigation.findNavController(it).navigate(R.id.action_shopFragment_to_productDetailFragment)
        }
    }
}