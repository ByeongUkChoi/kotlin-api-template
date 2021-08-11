package com.example.kotlinapitemplate.order.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class OrderTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun orderTest() {
        // given
        val userId = "cbw"

        val productId = 1L
        val quantity = 2
        val totalPrice = 3000L

        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
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
        ).andExpect(MockMvcResultMatchers.status().isOk)
    }
}