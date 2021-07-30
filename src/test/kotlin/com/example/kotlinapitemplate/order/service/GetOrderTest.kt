package com.example.kotlinapitemplate.order.service

import com.example.kotlinapitemplate.order.Order
import com.example.kotlinapitemplate.order.OrderRepository
import com.example.kotlinapitemplate.order.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class GetOrderTest {
    @Mock
    private lateinit var orderRepository: OrderRepository

    @InjectMocks
    private lateinit var orderService: OrderService

    @Test
    fun getOrderTest() {
        // given
        val userId = "cbw"
        val orderId = 1L
        val productId = 2L
        val quantity = 3
        val totalPrice = 4000L

        `when`(orderRepository.findByIdAAndOrdererId(orderId, userId))
            .thenReturn(Optional.of(Order(orderId, userId, productId, quantity, totalPrice)))

        // when
        val order = orderService.getOrder(userId, orderId)

        // then
        assertEquals(order.id, orderId)
        assertEquals(order.ordererId, userId)
        assertEquals(order.productId, productId)
        assertEquals(order.quantity, quantity)
        assertEquals(order.totalPrice, totalPrice)
    }
}