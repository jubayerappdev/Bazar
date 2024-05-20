package com.creativeitinstitute.bazar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.creativeitinstitute.bazar.databinding.ProductItemBinding
import com.creativeitinstitute.bazar.views.dashboard.seller.MyProductFragment.Product

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ProductItemBinding):RecyclerView.ViewHolder(binding.root)


    private val differCallback =  object :DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
          return  oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =differ.currentList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        differ.currentList[position].let {products ->
            with(holder.binding){
                tvProductName.text = products.productName
                tvProductPrice.text = products.productPrice
                tvProductStatus.text= products.productStatus
            }
        }
    }
}