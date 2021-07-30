package com.example.kotlinapitemplate.order.controller

import com.example.kotlinapitemplate.order.OrderController
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

        // TODO: mock controller params
        doNothing().`when`(orderController).order(userId)

        // when & then
        mockMvc.perform(
            post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
                .content(
                    """
                    [
                        {
                            "productId": 1,
                            "quantity": 2,
                            "totalPrice": 2000
                        },
                        {
                            "productId": 2,
                            "quantity": 3,
                            "totalPrice": 6000
                        }
                    ]
                """.trimIndent()
                )
        ).andExpect(status().isOk)
    }
}