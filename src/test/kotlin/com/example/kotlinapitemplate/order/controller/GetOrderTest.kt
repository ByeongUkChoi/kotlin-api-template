package com.example.kotlinapitemplate.order.controller

import com.example.kotlinapitemplate.order.Order
import com.example.kotlinapitemplate.order.OrderController
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class GetOrderTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var orderController: OrderController

    @Test
    fun getOrderTest() {
        // given
        val userId = "cbw"
        val orderId = 1L
        val productId = 2L
        val quantity = 3
        val totalPrice = 4000L

        `when`(orderController.getOrder(userId, orderId))
            .thenReturn(Order(orderId, userId, productId, quantity, totalPrice))

        // when & then
        mockMvc.perform(
            get("/orders/$orderId")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", `is`(orderId.toInt())))
            .andExpect(jsonPath("$.productId", `is`(productId.toInt())))
            .andExpect(jsonPath("$.quantity", `is`(quantity)))
            .andExpect(jsonPath("$.totalPrice", `is`(totalPrice.toInt())))
    }

    @Test
    fun `when empty header failure test`() {
        // given
        val userId = "cbw"
        val orderId = 1L
        val productId = 2L
        val quantity = 3
        val totalPrice = 4000L

        `when`(orderController.getOrder(userId, orderId))
            .thenReturn(Order(orderId, userId, productId, quantity, totalPrice))

        // when & then
        mockMvc.perform(
            get("/orders/$userId")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
    }
}