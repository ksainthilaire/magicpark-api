package com.magicpark.api.services

import com.google.gson.Gson
import com.magicpark.api.OrangeApi
import com.magicpark.api.enums.OrderStatus
import com.magicpark.api.enums.PaymentMethod
import com.magicpark.api.model.database.Order
import com.magicpark.api.model.database.User
import com.magicpark.api.model.database.UserTicket
import com.magicpark.api.model.request.order.CreateOrderRequest
import com.magicpark.api.model.request.order.ShopItemOrderRequest
import com.magicpark.api.payments.orange.Orange
import com.magicpark.api.repositories.IOrderRepository
import com.magicpark.api.repositories.IShopRepository
import com.magicpark.api.repositories.IUserRepository
import com.magicpark.api.repositories.IUserTicketRepository
import com.magicpark.api.utils.fromJson
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class OrderService(
    val orderRepository: IOrderRepository,
    val userRepository: IUserRepository,
    val userTicketRepository: IUserTicketRepository,
    val shopRepository: IShopRepository,
    val orangeApi: OrangeApi
) {
    fun getAllOrders(): List<Order> = orderRepository.findAll()

    fun getOrderById(id: Long): Order =
        orderRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun updateOrder(id: Long, Shop: Order) {
        if (orderRepository.existsById(id)) {
            Shop.id = id
            orderRepository.save(Shop)
        }
    }

    fun createWallet(user: User, order: Order) {
        val cart: List<ShopItemOrderRequest> = Gson().fromJson(order.cart!!)
        for (item in cart) {
            val result = shopRepository.findById(item.id)
            if (result.isPresent) {
                val shopItem = result.get()
                val ticket = UserTicket(
                    name = shopItem.name,
                    userId = user.id,
                    imageUrl = shopItem.imageUrl,
                    backgroundColor = shopItem.backgroundColor,
                    shopItem = shopItem.id,
                )
                userTicketRepository.save(ticket)
            } else {
                throw ResponseStatusException(HttpStatus.NOT_FOUND)
            }
        }
    }

    fun validateOrder(number: String): ResponseEntity<Any> {
        val orderResult = orderRepository.findByNumber(number)
        return if (orderResult.isPresent) {

            val order = orderResult.get()
            order.status = OrderStatus.VALID
            orderRepository.save(order)

            val userResult = userRepository.findById(order.userId!!)
            if (userResult.isPresent) {
                createWallet(userResult.get(), order)
                ResponseEntity.status(HttpStatus.OK).build()
            } else ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    fun createOrder(userId: Long, order: CreateOrderRequest): ResponseEntity<Any> {

        var totalAmount = 0f

        for (item in order.items) {
            val result = shopRepository.findById(item.id!!)
            if (result.isPresent) {
                val quantity = item.quantity ?: 1
                val shopItem = result.get()
                totalAmount += (shopItem.price ?: 0f) * quantity
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }

        val cart = Gson().toJson(order.items)

        println("cart (${cart})")

        val order = Order(
            number = UUID.randomUUID().toString().substring(0, 20),
            paymentMethod = order.paymentMethod,
            userId = userId,
            cart = cart,
            totalAmount = totalAmount,
            status = OrderStatus.PENDING
        )
        orderRepository.save(order)

        when (order.paymentMethod) {
            PaymentMethod.ORANGE -> {
                orangeApi.createTransaction(
                    Orange.getInstance().getAccessToken(),
                    Orange.getInstance().createTransaction(order)
                ).subscribe({
                    println("api response ${it}")
                }, {
                    println("error ${it.message}")
                })
            }
            else -> throw Error("payment method?")
        }

        return ResponseEntity.status(HttpStatus.OK).build()
    }
}