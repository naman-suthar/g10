package com.vrcareer.g10assignment.api

class ApiHelper (private val apiInterface: MyApi) {
    suspend fun getProductList() = apiInterface.getProductList()
}