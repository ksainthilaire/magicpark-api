package com.magicpark.api.payments.orange.model

import com.google.gson.annotations.SerializedName

data class TokenRequest(
    @SerializedName("grant_type")
    val grantType: String = "client_credentials"
)