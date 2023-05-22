package com.example.cleaningapp.cleaner.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.cleaner.uistate.ProductItemUiState
import com.example.cleaningapp.databinding.ItemFatrueiShopProductBinding

class ShopAdapter : ListAdapter<ProductItemUiState, ShopAdapter.MyViewHodler>(DiffCallBack()) {
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

    class MyViewHodler(val binding: ItemFatrueiShopProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(id: Int, product: ProductItemUiState) {
            with(binding) {
                ivShopProductPicture.setImageResource(product.image)
                tvShopProductName.text = product.name
                tvShopProductPrice.text = product.price.toString()
                ivShopProductPicture.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("id", id)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_shopFragment_to_productDetailFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHodler {
        return MyViewHodler(
            ItemFatrueiShopProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        holder.onBind(getItem(position).id, getItem(position))
    }
}