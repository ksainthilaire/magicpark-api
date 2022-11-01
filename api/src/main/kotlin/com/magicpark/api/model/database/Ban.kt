package com.magicpark.api.model.database

import javax.persistence.*

@Entity
@Table(name = "magicpark_bans")
data class Ban(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,

    @Column(name = "order_id")
    var orderId: Int? = null,

    var reason: String? = null,
)