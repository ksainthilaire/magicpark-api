package com.magicpark.api.model.database

import java.sql.Timestamp
import javax.persistence.*

@Entity
@Table(name = "magicpark_user_voucher")
data class Voucher(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,

    @Column(name = "voucher_code")
    var voucherCode: String? = null,

    @Column(name = "product_id")
    var productId: Int? = null,

    @Column(name = "expires_at")
    var expiresAt: Timestamp? = null
)