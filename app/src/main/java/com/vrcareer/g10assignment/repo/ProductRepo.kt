package com.vrcareer.g10assignment.repo

import com.vrcareer.g10assignment.api.ApiHelper

class ProductRepo(private val apiHelper: ApiHelper) {
    suspend fun getAllProductList() = apiHelper.getProductList()
}