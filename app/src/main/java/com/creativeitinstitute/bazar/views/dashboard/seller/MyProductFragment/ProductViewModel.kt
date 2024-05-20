package com.creativeitinstitute.bazar.views.dashboard.seller.MyProductFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeitinstitute.bazar.core.DataState

class ProductViewModel :ViewModel(){

    private val _products:MutableLiveData<DataState<MutableList<Product>>> = MutableLiveData()

    val products:MutableLiveData<DataState<MutableList<Product>>> = _products



}