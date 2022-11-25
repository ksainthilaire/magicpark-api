package com.magicpark.api.model.database

import com.fasterxml.jackson.annotation.JsonAlias
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
@Table(name = "magicpark_shop")
data class ShopItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    @Size
    @Column(name="name")
    val name: String? = null,

    @Column(name="description")
    var description: String? = null,

    @Column(name="image_url")
    @JsonAlias("image_url")
    var imageUrl: String? = null,

    @Column(name="background_color")
    @JsonAlias("background_color")
    var backgroundColor: String? = null,

    @Column(name="categories")
    @JsonAlias("categories")
    var categories: String? = null,

    @Column(name="price")
    var price: Float? = null,

    @Column(name="quantity")
    var quantity: Int? = null,

    @Column(name="is_pack")
    var isPack: Boolean? = false,

    @Column(name="pack_quantity")
    @JsonAlias("pack_quantity")
    var packQuantity: Int? = 0,

    @Column(name="pack_shop_item_id")
    @JsonAlias("pack_shop_item_id")
    var packShopItemId: Int? = null
)
