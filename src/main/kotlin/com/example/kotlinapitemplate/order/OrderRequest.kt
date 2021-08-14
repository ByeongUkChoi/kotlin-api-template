package com.example.kotlinapitemplate.order

import javax.validation.constraints.Positive

data class OrderRequest(
    @field:Positive var productId: Long,
    @field:Positive val quantity: Int,
    @field:Positive val totalPrice: Long
)