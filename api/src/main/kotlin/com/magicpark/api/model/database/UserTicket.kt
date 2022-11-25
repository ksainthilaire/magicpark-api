package com.magicpark.api.model.database

import com.fasterxml.jackson.annotation.JsonAlias
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "magicpark_wallets")
data class UserTicket(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,

    @Column(name="name")
    val name: String? = null,

    @Column(name="image_url")
    @JsonAlias("image_url")
    var imageUrl: String? = null,

    @Column(name="background_color")
    @JsonAlias("background_color")
    var backgroundColor: String? = null,

    @Column(name="shop_item")
    @JsonAlias("shop_item")
    val shopItem: Long? = null,

    @Column(name="user_id")
    @JsonAlias("user_id")
    val userId: Long? = null,

    @Column(name="created_at")
    @JsonAlias("created_at")
    val createdAt: Date? = null,

    @Column(name="expired_at")
    @JsonAlias("expired_at")
    val expiredAt: Date? = null
)
