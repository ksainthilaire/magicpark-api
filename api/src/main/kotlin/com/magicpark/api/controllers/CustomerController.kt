package com.magicpark.api.controllers

import com.magicpark.api.model.database.ShopItem
import com.magicpark.api.model.database.User
import com.magicpark.api.model.request.authentification.LoginRequest
import com.magicpark.api.model.request.authentification.UpdateUserRequest
import com.magicpark.api.model.request.order.CreateOrderRequest
import com.magicpark.api.model.response.user.UserResponse
import com.magicpark.api.services.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/customer")
class CustomerController @Autowired constructor(
    val userService: UserService,
    val shopService: ShopService,
    val orderService: OrderService,
    val walletService: WalletService
) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: LoginRequest): ResponseEntity<Any> =  userService.login(request)

    @GetMapping("/user")
    fun getUser(@AuthenticationPrincipal user: User): ResponseEntity<Any> = userService.getUser(user.id!!)

    @PutMapping("/user")
    fun updateUser(@AuthenticationPrincipal user: User, @RequestBody @Valid request: UpdateUserRequest): Unit =  userService.updateUser(user, request)

    @GetMapping("/shop")
    fun getAllShopItems() = shopService.getAllShopItems()

    @GetMapping("/shop/categories")
    fun getShopCategories() = shopService.getShopCategories()

    @GetMapping("/test")
    fun test() = ResponseEntity.ok().body("test")

    @PostMapping("/order")
    fun createOrder(@AuthenticationPrincipal user: User, @RequestBody body: CreateOrderRequest) = orderService.createOrder(user.id!!, body)

    @GetMapping("/order/{id}")
    fun getOrder(@PathVariable("id") id: Long) = orderService.getOrderById(id)

    @GetMapping("/wallet")
    fun createOrder(@AuthenticationPrincipal user: User) = walletService.getWalletById(user.id!!)
}

