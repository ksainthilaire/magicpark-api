package com.magicpark.api.model.database

import com.magicpark.api.enums.ShopType
import javax.persistence.*

@Entity
@Table(name = "magicpark_shop")
data class Shop(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    var name: String? = null,



    var type: ShopType? = ShopType.NORMAL,

    /*
        The translation, list of texts associated with this shop.
     */
    var translationId: Long? = null,
)
