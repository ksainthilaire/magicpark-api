package com.magicpark.api.model.database

import javax.persistence.*

@Entity
@Table(name = "magicpark_orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,

    @Column(name = "user_id")
    var userId: Long? = null,

    @Column(name = "transaction_id")
    var transactionId: Long? = null,

    @Column(name = "product_id")
    var productId: Long? = null,
    var quantity: Long? = null,
    var status: Long? = null
)