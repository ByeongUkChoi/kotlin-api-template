package com.example.kotlinapitemplate.order

data class OrderRequest(
    val productId: Long,
    val quantity: Int,
    val totalPrice: Long
)