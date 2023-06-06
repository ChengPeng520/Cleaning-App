package com.example.cleaningapp.backstage.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemAlbBsShopDataboxBinding

class BackstageShopProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<BackstageShopProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(val ItemViewBinding:ItemAlbBsShopDataboxBinding) :
        ViewHolder(ItemViewBinding.root)


    @SuppressLint("NotifyDataSetChanged")
    fun updateProduct(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemViewBinding =
            ItemAlbBsShopDataboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        itemViewBinding.viewModel = BsShopProductViewModel()
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()

        return ProductViewHolder(itemViewBinding)

    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = products[position]
        with(holder) {
            ItemViewBinding.viewModel?.product?.value = product
            val bundle = Bundle()
            bundle.putSerializable("product", product)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_bsShopMainFragment_to_bsShopProductModifyFragment, bundle)
            }

        }


    }
}
