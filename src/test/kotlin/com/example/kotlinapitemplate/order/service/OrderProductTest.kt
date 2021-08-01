package com.example.kotlinapitemplate.order.service

import com.example.kotlinapitemplate.order.Order
import com.example.kotlinapitemplate.order.OrderRepository
import com.example.kotlinapitemplate.order.OrderRequest
import com.example.kotlinapitemplate.order.OrderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class OrderProductTest {
    @Mock
    private lateinit var orderRepository: OrderRepository

    @InjectMocks
    private lateinit var orderService: OrderService

    @Captor
    private lateinit var captor: ArgumentCaptor<Order>

    @Test
    fun orderProductTest() {
        // given
        val userId = "cbw"
        val productId = 2L
        val quantity = 3
        val totalPrice = 4000L

        // when
        orderService.orderProduct(OrderRequest(productId, quantity, totalPrice), userId)

        // then
        verify(orderRepository).save(MockitoHelper.capture(captor))
        val order = captor.value

        assertEquals(order.ordererId, userId)
        assertEquals(order.productId, productId)
        assertEquals(order.quantity, quantity)
        assertEquals(order.totalPrice, totalPrice)
    }
}

object MockitoHelper {
    // use this in place of captor.capture() if you are trying to capture an argument that is not nullable
    fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
}