package com.example.cleaningapp.cleaner.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.cleaner.uistate.ShoppingCartItemUiState
import com.example.cleaningapp.cleaner.viewmodel.shop.ShoppingCartViewModel
import com.example.cleaningapp.databinding.ItemFatrueiShoppingCartProductBinding

class ShoppingCartAdapter :
    ListAdapter<ShoppingCartItemUiState, ShoppingCartAdapter.ItemViewHolder>(DiffCallBack()) {
    private lateinit var clickInterface: ClickInterface

    interface ClickInterface {
        fun onBtnClick(productId: ShoppingCartItemUiState)
    }

    fun setOnclick(clickInterface: ClickInterface) {
        this.clickInterface = clickInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = ItemFatrueiShoppingCartProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        itemView.viewModel = ShoppingCartViewModel()
        itemView.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        with(holder.itemBinding) {
            viewModel?.adapterUiState?.value = getItem(position)
            ivShoppingCartProductDelete.setOnClickListener {
                clickInterface.onBtnClick(getItem(position))
            }
        }
    }

    class ItemViewHolder(val itemBinding: ItemFatrueiShoppingCartProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    class DiffCallBack : DiffUtil.ItemCallback<ShoppingCartItemUiState>() {
        override fun areItemsTheSame(
            oldItem: ShoppingCartItemUiState,
            newItem: ShoppingCartItemUiState
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ShoppingCartItemUiState,
            newItem: ShoppingCartItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }
}