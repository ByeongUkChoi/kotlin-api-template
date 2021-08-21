package com.example.kotlinapitemplate.order.api

import com.example.kotlinapitemplate.error.exception.ErrorCode
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class GetOrderTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @Sql(statements = ["INSERT INTO ORDERS(ID, ORDERER_ID, PRODUCT_ID, QUANTITY, TOTAL_PRICE) VALUES (1001, 'get-order-test-1', 2, 3, 4000)"])
    fun getOrderTest() {
        // given (sql script)
        val userId = "get-order-test-1"
        val orderId = 1001L
        val productId = 2L
        val quantity = 3
        val totalPrice = 4000L

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
    fun `when get order api without header failure test`() {
        // TODO: add exception

        // when & then
        mockMvc.perform(
            get("/orders/1")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `when get order api not found order failure test`() {
        // when & then
        mockMvc.perform(
            get("/orders/9999")
                .accept(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", "cbw")
        )
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.code", `is`(ErrorCode.NOT_FOUND_ORDER.code)))
            .andExpect(jsonPath("$.message", `is`(ErrorCode.NOT_FOUND_ORDER.message)))
    }

}