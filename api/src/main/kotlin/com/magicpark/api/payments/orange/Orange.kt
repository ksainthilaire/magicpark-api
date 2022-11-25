package com.magicpark.api.payments.orange

import com.magicpark.api.model.database.Order
import com.magicpark.api.payments.orange.model.TransactionRequest

class Orange private constructor() {

    private lateinit var accessToken: String

    fun setAccessToken(token: String) {
        println("token set ${token}")
        accessToken = token
    }

    fun getAccessToken() = "Bearer ${accessToken}"

    fun createTransaction(order: Order): TransactionRequest {
        return TransactionRequest(
            merchantKey = ORANGE_MERCHANT_KEY,
            returnUrl = String.format(RETURN_URL, ""),
            cancelUrl = String.format(CANCEL_URL, ""),
            notifUrl = String.format(NOTIFICATION_URL, ""),
            lang = "fr",
            currency = "OUV",
            orderId = order.id.toString(),
            amount = order.totalAmount.toString(),
            reference = "Magicpark"
        )
    }

    companion object {

        @Volatile
        private lateinit var instance: Orange

        fun getInstance(): Orange {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = Orange()
                }
                return instance
            }
        }

        const val RETURN_URL = "https://magicpark-gn.com/?success"
        const val CANCEL_URL = "https://magicpark-gn.com/?cancel"
        const val NOTIFICATION_URL = "https://magicpark-gn.com/?cancel"

        const val ORANGE_MERCHANT_KEY = "WP01226"
        const val ORANGE_API_TOKEN = "Basic dVJhMHBHc1kzcUFoUTFJaVN3NWFXVGxneGtVVkdEVkI6elZpa3V5Y3pjTk1HV1Ewcg=="
    }

}