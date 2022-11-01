package com.magicpark.api.model.request.order

import com.magicpark.api.enums.DeliveryType
import com.magicpark.api.enums.PaymentMethod
import com.magicpark.api.model.request.base.IRequest
import javax.persistence.Column

data class CreateOrderRequest(
    /* Match a discount code before order purchase */
    val products: List<Int>? = null,

    /* Match a discount code before order purchase */
    @Column(name = "voucher_code")
    val voucherCode: String? = null,

    /* Corresponds to the address ID where the user must have his order delivered */
    @Column(name = "address_id")
    val addressId: Int? = null,

    /* Corresponds to the type of delivery
            DeliveryType.NORMAL => Normal delivery, the customer receives his order within a minimum of 8 days
            DeliveryType.EXPRESS => Accelerated delivery, the customer receives the order in the shortest possible time */
    @Column(name = "delivery_type")
    val deliveryType: DeliveryType = DeliveryType.NORMAL,

    /* Corresponds to the payment method that was chosen by the buyer */
    @Column(name = "payment_method")
    val paymentMethod: PaymentMethod = PaymentMethod.PAYPAL
) : IRequest