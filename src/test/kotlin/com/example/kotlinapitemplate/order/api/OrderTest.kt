package com.example.kotlinapitemplate.order.api

import com.example.kotlinapitemplate.error.exception.ErrorCode
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
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

    @Test
    fun `when order api empty header failure test`() {
        // given
        val productId = 1L
        val quantity = 2
        val totalPrice = 3000L

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "productId": $productId,
                        "quantity": $quantity,
                        "totalPrice": $totalPrice
                    }
                """.trimIndent()
                )
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun `when order api invalid body failure test`() {
        // given
        val userId = "cbw"

        val productId = 1L
        val quantity = 2
        val totalPrice = 3000L

        val contentWithoutProductId = """
                    {
                        "quantity": $quantity,
                        "totalPrice": $totalPrice
                    }
        """.trimIndent()
        val contentWithoutQuantity = """
                    {
                        "productId": $productId,
                        "totalPrice": $totalPrice
                    }
        """.trimIndent()
        val contentWithoutTotalPrice = """
                    {
                        "productId": $productId,
                        "quantity": $quantity
                    }
        """.trimIndent()

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
                .content(contentWithoutProductId)
        )
            .andExpect(status().`is`(ErrorCode.MISSING_REQUIRED_VALUES.httpStatus.value()))
            .andExpect(jsonPath("$.code", `is`(ErrorCode.MISSING_REQUIRED_VALUES.code)))
            .andExpect(jsonPath("$.message", `is`(ErrorCode.MISSING_REQUIRED_VALUES.message)))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
                .content(contentWithoutQuantity)
        )
            .andExpect(status().`is`(ErrorCode.MISSING_REQUIRED_VALUES.httpStatus.value()))
            .andExpect(jsonPath("$.code", `is`(ErrorCode.MISSING_REQUIRED_VALUES.code)))
            .andExpect(jsonPath("$.message", `is`(ErrorCode.MISSING_REQUIRED_VALUES.message)))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-USER-ID", userId)
                .content(contentWithoutTotalPrice)
        )
            .andExpect(status().`is`(ErrorCode.MISSING_REQUIRED_VALUES.httpStatus.value()))
            .andDo(print())
            .andExpect(jsonPath("$.code", `is`(ErrorCode.MISSING_REQUIRED_VALUES.code)))
            .andExpect(jsonPath("$.message", `is`(ErrorCode.MISSING_REQUIRED_VALUES.message)))
    }
}