package com.example.cleaningapp.backstage.shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.R
import com.example.cleaningapp.databinding.ItemAlbBsShopDataboxBinding

class ProductAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }

    class ProductViewHolder(val itemViewBinding: ItemAlbBsShopDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemViewBinding = ItemAlbBsShopDataboxBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        itemViewBinding.viewModel = BsShopProductViewModel()
        // 設定lifecycleOwner方能監控LiveData資料變化，layout檔案的view才會更新顯示
        itemViewBinding.lifecycleOwner = parent.findViewTreeLifecycleOwner()
        return ProductViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        with(holder) {
            // 將欲顯示的product物件指派給LiveData，就會自動更新layout檔案的view顯示
            itemViewBinding.viewModel?.product?.value = product
            //綁定photoBitmap
            itemViewBinding.ivBsShopDataboxPhoto.setImageBitmap(product.photoBitmap)
            val bundle = Bundle()
            bundle.putInt("productId", product.productId)
            itemView.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.bsShopProductModifyFragment,
                        bundle)
            }

//            with(holder) {
//                itemViewBinding.viewModel?.coupon?.value = coupon
//                itemViewBinding.btCusCouponPick.setOnClickListener {
//                    Navigation.findNavController(it).previousBackStackEntry?.savedStateHandle?.set("coupon", coupon)
//                    Navigation.findNavController(it).popBackStack()
//                }
//            }
        }
    }
}
