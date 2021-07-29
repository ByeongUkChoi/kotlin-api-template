package com.example.kotlinapitemplate.order

import javax.persistence.*

@Entity(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    val productId: Long,
    val quantity: Int,
    val totalPrice: Long) {
}