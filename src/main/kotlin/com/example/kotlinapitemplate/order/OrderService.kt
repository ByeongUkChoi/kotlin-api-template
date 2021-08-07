package com.example.kotlinapitemplate.order

import com.example.kotlinapitemplate.error.exception.BusinessException
import com.example.kotlinapitemplate.error.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {
    fun orderProduct(orderRequest: OrderRequest, ordererId: String) {
        val order = Order(ordererId, orderRequest.productId, orderRequest.quantity, orderRequest.totalPrice)
        orderRepository.save(order)
    }

    fun getOrders(userId: String): List<Order> = orderRepository.findAllByOrdererId(userId).toList()

    fun getOrder(userId: String, orderId: Long): Order {
        val optionalOrder = orderRepository.findByIdAndOrdererId(orderId, userId)
        if (optionalOrder.isEmpty) {
            throw BusinessException(ErrorCode.NOT_FOUND_ORDER)
        }
        return optionalOrder.get()
    }
}