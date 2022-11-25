package com.magicpark.api.model.database

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "magicpark_shop_category")
data class ShopCategory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Size
    @Column(name="name")
    val name: String? = null
)
