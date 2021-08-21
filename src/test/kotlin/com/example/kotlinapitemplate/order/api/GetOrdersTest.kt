package com.example.kotlinapitemplate.order.api

import com.example.kotlinapitemplate.error.exception.ErrorCode
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class GetOrdersTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun getOrdersTest() {
        // given
        val userId = "get-order-test-from-empty-orderer"

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
    fun `when get orders api without header failure test`() {
        // when & then
        mockMvc.perform(
            get("/orders")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().`is`(ErrorCode.MISSING_REQUEST_HEADER.httpStatus.value()))
            .andExpect(jsonPath("$.code", `is`(ErrorCode.MISSING_REQUEST_HEADER.code)))
            .andExpect(jsonPath("$.message", `is`(ErrorCode.MISSING_REQUEST_HEADER.message)))
    }
}