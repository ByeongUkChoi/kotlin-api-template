package com.example.kotlinapitemplate.order

import org.springframework.data.repository.Repository
import java.util.*

interface OrderRepository : Repository<Order, Long> {
    fun save(order: Order): Order
    fun findByIdAndOrdererId(id: Long, ordererId: String): Optional<Order>
    fun findAllByOrdererId(ordererId: String): Iterable<Order>
}