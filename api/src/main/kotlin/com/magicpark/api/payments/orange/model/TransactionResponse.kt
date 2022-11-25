package com.magicpark.api.payments.orange.model

import com.google.gson.annotations.SerializedName

data class TransactionResponse(
    val status: Int? = null,
    val message: String? = null,

    @SerializedName("pay_token")
    val payToken: String? = null,

    @SerializedName("payment_url")
    val paymentUrl: String? = null,

    @SerializedName("notif_token")
    val notifToken: String? = null
)