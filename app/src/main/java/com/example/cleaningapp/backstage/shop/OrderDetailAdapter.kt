package com.example.cleaningapp.backstage.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaningapp.backstage.shop.viewModel.BsShopOrderDetailViewModel
import com.example.cleaningapp.databinding.ItemAlbBsShopOrderDetailDataboxBinding

class OrderDetailAdapter(private var ordersDetail: List<OrderDetail>) :
    RecyclerView.Adapter<OrderDetailAdapter.OrderDetailHolder>() {

    class OrderDetailHolder(val itemViewBinding: ItemAlbBsShopOrderDetailDataboxBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root)

 fun updateDetail(ordersDetail: List<OrderDetail>) {
        this.ordersDetail = ordersDetail
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderDetailHolder {
        //step1 建立要binding的畫面的
        val itemViewBinding =ItemAlbBsShopOrderDetailDataboxBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        //step2 建立畫面要viewModel連結的data,(前提layout檔要先建立viewModel)
        itemViewBinding.viewModel = BsShopOrderDetailViewModel()
        //step3 建立畫面與資料綁定父類別生命週期
        itemViewBinding.lifecycleOwner =parent.findViewTreeLifecycleOwner()
        //回傳此ViewHolder 的方法名稱,括號內要放建立的參數
        return OrderDetailHolder(itemViewBinding)

    }

    override fun getItemCount(): Int {
        return ordersDetail.size
    }

    override fun onBindViewHolder(holder: OrderDetailHolder, position: Int) {
        val orderDetail = ordersDetail[position]
        with(holder){
            itemViewBinding.viewModel?.ordersDetail?.value =orderDetail
            val bundel =Bundle()
            bundel.putSerializable("orderDetail",orderDetail)
            }
        }
    }
