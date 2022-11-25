package com.magicpark.api.controllers

import com.magicpark.api.model.database.Order
import com.magicpark.api.model.database.User
import com.magicpark.api.model.request.order.CreateOrderRequest
import com.magicpark.api.services.OrderService
import com.magicpark.api.services.ShopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/order")
class OrderController @Autowired constructor(val service: OrderService) {

    @GetMapping("")
    fun getAllOrders() = service.getAllOrders()

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long) = service.getOrderById(id)

    @PutMapping("/{id}")
    fun updateOrder(
        @PathVariable id: Long, @RequestBody Order: Order
    ) = service.updateOrder(id, Order)

    @PostMapping("/validate/{number}")
    fun validateOrder(
        @PathVariable number: String
    ) = service.validateOrder(number)

    @PostMapping("")
    fun createOrder(@AuthenticationPrincipal user: User, @RequestBody body: CreateOrderRequest) = service.createOrder(user.id!!, body)
}

