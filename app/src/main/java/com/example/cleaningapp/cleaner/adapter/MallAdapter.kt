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

class MallAdapter : ListAdapter<ProductItemUiState, MallAdapter.MyViewHodler>(DiffCallBack()) {
    private var itemWidth: Int = 178

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
        fun onBind(id: Int, product: ProductItemUiState, itemWidth: Int) {
            with(binding) {
                clMall.maxWidth = itemWidth
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
        this.itemWidth = parent.width / 2
        return MyViewHodler(
            ItemFatrueiShopProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHodler, position: Int) {
        holder.onBind(getItem(position).id, getItem(position), itemWidth)
    }
}