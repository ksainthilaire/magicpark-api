package com.magicpark.api.model.database

import javax.persistence.*

@Entity
@Table(name = "magicpark_products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    var price: Double? = null
)