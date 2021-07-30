package com.example.kotlinapitemplate.order

import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun orderProduct() {}

    fun getOrders(userId: String): List<Order> = orderRepository.findAllByOrdererId(userId).toList()

    fun getOrder(userId: String, orderId: Long): Order {
        val optionalOrder = orderRepository.findByIdAAndOrdererId(orderId, userId)
        if (optionalOrder.isEmpty) {
            // TODO: exception
        }
        return optionalOrder.get()
    }
}