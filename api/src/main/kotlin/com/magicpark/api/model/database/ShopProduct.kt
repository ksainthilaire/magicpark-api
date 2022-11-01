package com.magicpark.api.model.database

import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "magicpark_shop")
data class ShopProduct(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    @Size
    @Column(name = "shop_id")
    var shopId: Integer? = null,

    @Size
    @Column(name = "product_id")
    var productId: Integer? = null,

    var quantity: Integer? = null
)
