package com.example.kotlinapitemplate.order.api

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class GetOrdersTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getOrdersTest() {
        // given
        val userId = "cbw"

        // when & then
        mockMvc.perform(
            get("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
        )
            .andExpect(status().isOk)
            .andExpect(content().json("[]"))
    }

    @Test
    fun `when get order api without header failure test`() {
        // when & then
        mockMvc.perform(
            get("/orders")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
    }
}