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

interface MyCallInterface {
    fun onClick(productId: ShoppingCartItemUiState)
}

class ShoppingCartAdapter(myCallInterface: MyCallInterface) :
    ListAdapter<ShoppingCartItemUiState, ShoppingCartAdapter.ItemViewHolder>(ShoppingCartDiffCallBack()) {
    private val myCallInterface: MyCallInterface

    init {
        this.myCallInterface = myCallInterface
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
        holder.itemBinding.viewModel?.adapterUiState?.value = getItem(position)
        holder.itemBinding.ivShoppingCartProductDelete.setOnClickListener {
            myCallInterface.onClick(getItem(position))
        }
    }

    class ItemViewHolder(val itemBinding: ItemFatrueiShoppingCartProductBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    class ShoppingCartDiffCallBack : DiffUtil.ItemCallback<ShoppingCartItemUiState>() {
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