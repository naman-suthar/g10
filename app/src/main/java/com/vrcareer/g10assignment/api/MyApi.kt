package com.vrcareer.g10assignment.api

import com.vrcareer.g10assignment.model.ApiResult
import retrofit2.http.GET

interface MyApi {

    @GET("products")
    suspend fun getProductList(): ApiResult
}