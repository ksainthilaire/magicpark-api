package com.magicpark.api.model.request.order

import com.magicpark.api.model.request.base.IRequest

class RefundOrderRequest(
    /*
        List of products for which the customer is requesting a refund.
        If the list is zero, the buyer wants to be reimbursed for the entire order.
     */
    val products: List<Long>? = null
) : IRequest