package com.magicpark.api.enums

enum class OrderStatus {
    /*
        The order has been refunded. It is closed permanently.
     */
    REFUNDED,

    /*
        The order has been delivered.
     */
    DELIVERED,

    /*
        Order awaiting validation by support
     */
    PENDING,

    /*
         The order is awaiting payment
     */
    PAYMENT,

    /*
        The order has been canceled
     */
    CANCELED,

    /*
        The order is awaiting validation so that it can be refunded at the customer's request.
     */
    REFUND_REQUEST
}