package com.magicpark.api

import com.magicpark.api.payments.orange.Orange.Companion.ORANGE_API_TOKEN
import com.magicpark.api.payments.orange.model.TransactionRequest
import com.magicpark.api.payments.orange.model.TransactionResponse
import com.magicpark.api.payments.orange.model.TokenResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*


interface OrangeApi {
    @FormUrlEncoded
    @POST("/oauth/v3/token")
    fun getAccessToken(@Header("Authorization") token: String = ORANGE_API_TOKEN,
    @Field("grant_type") grantType: String = "client_credentials") : Observable<TokenResponse>

    @POST("/orange-money-webpay/dev/v1/webpayment")
    fun createTransaction(@Header("Authorization") token: String, @Body body: TransactionRequest) : Observable<TransactionResponse>
}

