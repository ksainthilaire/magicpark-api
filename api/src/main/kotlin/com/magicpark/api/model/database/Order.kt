package com.magicpark.api.model.database

import com.fasterxml.jackson.annotation.JsonAlias
import com.magicpark.api.enums.OrderStatus
import com.magicpark.api.enums.PaymentMethod
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "magicpark_orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Column(name="number")
    var number: String? = null,

    @Column(name="total_amount")
    @JsonAlias("total_amount")
    var totalAmount: Float? = null,

    @Column(name="payment_method")
    @JsonAlias("payment_method")
    var paymentMethod: PaymentMethod? = null,

    @Column(name="user_id")
    @JsonAlias("user_id")
    var userId: Long? = null,

    @Column(name="cart")
    var cart: String? = null,

    @Column(name="status")
    var status: OrderStatus? = OrderStatus.PENDING,

    @Column(name="deleted_at")
    @JsonAlias("deleted_at")
    var deletedAt: Date? = null,

    @Column(name="created_at")
    @JsonAlias("created_at")
    var createdAt: Date? = null
)