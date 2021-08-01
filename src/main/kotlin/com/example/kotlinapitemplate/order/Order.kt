package com.example.kotlinapitemplate.order

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val ordererId: String,
    val productId: Long,
    val quantity: Int,
    val totalPrice: Long
) {
    constructor(
        ordererId: String,
        productId: Long,
        quantity: Int,
        totalPrice: Long
    ) : this(null, ordererId, productId, quantity, totalPrice)
}