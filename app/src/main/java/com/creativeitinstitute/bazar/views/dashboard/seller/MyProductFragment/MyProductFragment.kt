package com.creativeitinstitute.bazar.views.dashboard.seller.MyProductFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.creativeitinstitute.bazar.adapter.ProductAdapter
import com.creativeitinstitute.bazar.base.BaseFragment
import com.creativeitinstitute.bazar.databinding.FragmentMyProductBinding


class MyProductFragment : BaseFragment<FragmentMyProductBinding>(FragmentMyProductBinding::inflate) {

    private val productAdapter = ProductAdapter()
    override fun onViewCreated() {


        val demoList = mutableListOf<Product>(
            Product("", "", "Nike Air Max 2017","Trading","230",""),
            Product("", "", "Nike Air Max 2018","Trading","233",""),
            Product("", "", "Nike Air Max 2019","Trading","244",""),
            Product("", "", "Nike Air Max 2019","Trading","244",""),
            Product("", "", "Nike Air Max 2019","Trading","244",""),
            Product("", "", "Nike Air Max 2019","Trading","244",""),
            Product("", "", "Nike Air Max 2019","Trading","244",""),

        )
        productAdapter.differ.submitList(demoList)

        setupRecyclerView()

    }

    fun setupRecyclerView()= binding.rvProduct.apply {
        adapter = productAdapter
        layoutManager=GridLayoutManager(context,2)
    }



    override fun setListener() {
    }

    override fun allObserver() {
    }


}