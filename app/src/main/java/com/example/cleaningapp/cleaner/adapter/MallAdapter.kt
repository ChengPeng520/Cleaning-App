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

class MallAdapter : ListAdapter<ProductItemUiState, MallAdapter.MyViewHolder>(DiffCallBack()) {
    private var itemWidth: Int = 0

    class DiffCallBack : DiffUtil.ItemCallback<ProductItemUiState>() {
        override fun areItemsTheSame(
            oldItem: ProductItemUiState,
            newItem: ProductItemUiState
        ): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(
            oldItem: ProductItemUiState,
            newItem: ProductItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

    class MyViewHolder(val binding: ItemFatrueiShopProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(productId: Int, product: ProductItemUiState, itemWidth: Int) {
            with(binding) {
                clMall.maxWidth = itemWidth
                ivShopProductPicture.setImageBitmap(product.productPhoto)
                tvShopProductName.text = product.name
                tvShopProductPrice.text = product.price.toString()
                ivShopProductPicture.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("productId", productId)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_shopFragment_to_productDetailFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        this.itemWidth = parent.width / 2
        return MyViewHolder(
            ItemFatrueiShopProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position).productId, getItem(position), itemWidth)
    }
}