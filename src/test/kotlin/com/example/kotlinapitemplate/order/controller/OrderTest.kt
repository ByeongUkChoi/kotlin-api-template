package com.example.kotlinapitemplate.order.controller

import com.example.kotlinapitemplate.order.OrderController
import com.example.kotlinapitemplate.order.OrderRequest
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doNothing
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class OrderTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var orderController: OrderController

    @Test
    fun orderSuccessTest() {
        // given
        val userId = "cbw"

        val productId = 1L
        val quantity = 2
        val totalPrice = 3000L


        // TODO: mock controller params
        doNothing().`when`(orderController).order(userId, OrderRequest(productId, quantity, totalPrice))

        // when & then
        mockMvc.perform(
            post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
                .content(
                    """
                    {
                        "productId": $productId,
                        "quantity": $quantity,
                        "totalPrice": $totalPrice
                    }
                """.trimIndent()
                )
        ).andExpect(status().isOk)
    }
}