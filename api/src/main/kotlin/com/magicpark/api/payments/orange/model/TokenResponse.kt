package com.magicpark.api.payments.orange.model

import com.google.gson.annotations.SerializedName


data class TokenResponse(

    @SerializedName("token_type")
    val tokenType: String? = null,

    @SerializedName("access_token")
    val accessToken: String? = null,

    @SerializedName("expires_in")
    val expiresIn: String? = null
)

