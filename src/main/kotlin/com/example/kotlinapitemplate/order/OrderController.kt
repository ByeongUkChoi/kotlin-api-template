package com.example.kotlinapitemplate.order

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    fun order(@RequestHeader("X-USER-ID") userId: String) {
    }

    @GetMapping
    fun getOrders(@RequestHeader("X-USER-ID") userId: String): List<Order> {

        return ArrayList<Order>()
    }

    @GetMapping("/{orderId}")
    fun getOrder(@RequestHeader("X-USER-ID") userId: String,
                 @PathVariable orderId: Long): Order? {
        return null
    }
}