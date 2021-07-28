package com.example.kotlinapitemplate.order.api

import com.example.kotlinapitemplate.order.controller.OrderController
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
@AutoConfigureMockMvc
class OrderApiTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var orderController: OrderController

    @Test
    fun orderSuccessApiTest() {
        // given
        val userId = "cbw"

        // when
        // TODO: mock controller
        `when`(orderController.order())

        // then
        mockMvc.perform(
            post("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
                .content(
                    "[\n" +
                            "    {\n" +
                            "        \"productId\": 1,\n" +
                            "        \"quantity\": 2,\n" +
                            "        \"totalPrice\": 2000\n" +
                            "    },\n" +
                            "    {\n" +
                            "        \"productId\": 2,\n" +
                            "        \"quantity\": 3,\n" +
                            "        \"totalPrice\": 6000\n" +
                            "    }\n" +
                            "]"
                )
        ).andExpect(status().isOk)
    }
}