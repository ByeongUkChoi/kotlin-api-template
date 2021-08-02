package com.example.kotlinapitemplate.order.repository

import com.example.kotlinapitemplate.order.Order
import com.example.kotlinapitemplate.order.OrderRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataJpaTest
class OrderRepositoryTest {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Test
    fun saveOrderTest() {

        // given
        val userId = "cbw"
        val productId = 2L
        val quantity = 3
        val totalPrice = 4000L
        val order = Order(userId, productId, quantity, totalPrice)

        // when
        val savedOrder = orderRepository.save(order)

        // then
        assertNotNull(savedOrder.id)
        assertEquals(savedOrder.ordererId, userId)
        assertEquals(savedOrder.productId, productId)
        assertEquals(savedOrder.quantity, quantity)
        assertEquals(savedOrder.totalPrice, totalPrice)
    }
}