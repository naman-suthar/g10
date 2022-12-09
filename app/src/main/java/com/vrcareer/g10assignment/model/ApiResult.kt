package com.vrcareer.g10assignment.model

data class ApiResult(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)