package com.vrcareer.g10assignment.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.vrcareer.g10assignment.Status
import com.vrcareer.g10assignment.api.ApiHelper
import com.vrcareer.g10assignment.repo.ProductRepo

class ProductListViewModel(private val productRepo: ProductRepo): ViewModel() {
    fun getAllProduct() = liveData {
        emit(Status.LOADING)
        try {
            emit(Status.SUCCESS(productRepo.getAllProductList()))
        }catch (e: Exception) {
            emit(Status.FAILURE(e.message.toString()))
        }
    }
}

class ProductListViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductListViewModel::class.java)){
            return ProductListViewModel(ProductRepo(apiHelper)) as T
        }
        throw IllegalArgumentException("Class not found")
    }
}