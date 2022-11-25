package com.magicpark.api.model.request.order

import com.fasterxml.jackson.annotation.JsonAlias
import com.magicpark.api.enums.PaymentMethod
import com.magicpark.api.model.request.base.IRequest

data class ShopItemOrderRequest(
    @JsonAlias("id")
    val id: Long,

    @JsonAlias("quantity")
    var quantity: Long
)

data class CreateOrderRequest(
    @JsonAlias("items")
    val items: List<ShopItemOrderRequest>,

    @JsonAlias("voucher_code")
    val voucherCode: String? = null,

    @JsonAlias("payment_method")
    val paymentMethod: PaymentMethod
) : IRequest