package com.magicpark.api.model.database

import com.fasterxml.jackson.annotation.JsonAlias
import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "magicpark_voucher")
data class Voucher(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,

    @Column(name = "voucher_code")
    @JsonAlias("voucher_code")
    var voucherCode: String? = null,

    @Column(name = "shop_item")
    @JsonAlias("shop_item")
    var shopItem: Int? = null,

    @Column(name = "created_at")
    @JsonAlias("created_at")
    var createdAt: Timestamp? = null,

    @Column(name = "deleted_at")
    @JsonAlias("deleted_at")
    var deletedAt: Timestamp? = null
)