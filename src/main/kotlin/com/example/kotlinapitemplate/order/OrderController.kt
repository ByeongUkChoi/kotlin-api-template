package com.example.kotlinapitemplate.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun order(@RequestHeader("X-USER-ID") userId: String,
              @RequestBody orderRequest: OrderRequest) = orderService.orderProduct(orderRequest, userId)

    @GetMapping
    fun getOrders(@RequestHeader("X-USER-ID") userId: String): List<Order> = orderService.getOrders(userId)

    @GetMapping("/{orderId}")
    fun getOrder(
        @RequestHeader("X-USER-ID") userId: String,
        @PathVariable orderId: Long
    ): Order = orderService.getOrder(userId, orderId)
}