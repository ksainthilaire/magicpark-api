package com.magicpark.api.payments.orange.model

import com.google.gson.annotations.SerializedName

data class TransactionRequest(
    @SerializedName("merchant_key")
    val merchantKey: String? = null,
    val currency: String? = null,

    @SerializedName("order_id")
    val orderId: String? = null,
    val amount: String? = null,

    @SerializedName("return_url")
    val returnUrl: String? = null,

    @SerializedName("cancel_url")
    val cancelUrl: String? = null,

    @SerializedName("notif_url")
    val notifUrl: String? = null,
    val lang: String? = null,
    val reference: String? = null
)