package com.creativeitinstitute.bazar.views.dashboard.seller.upload

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.creativeitinstitute.bazar.data.Product
import com.creativeitinstitute.bazar.databinding.ItemSellerProductBinding

class SellerProductAdapter(val productList : List<Product>):RecyclerView.Adapter<SellerProductAdapter.ProductViewHolder>() {


    class ProductViewHolder(val binding: ItemSellerProductBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        return ProductViewHolder(ItemSellerProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int {
        return productList.size

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        productList[position].let {
            holder.binding.apply {
                textProductName.text = it.name
                textProductDescription.text = it.description
                textProductPrice.text= "Price: $ ${it.price}"
                textProductStock.text= "Stock: ${it.amount}"
                ivProduct.load(it.imageLink)
            }
        }

    }
}