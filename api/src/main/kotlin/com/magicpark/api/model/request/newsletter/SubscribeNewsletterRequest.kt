package com.magicpark.api.model.request.newsletter

import com.magicpark.api.model.request.base.IRequest


data class SubscribeNewsletterRequest(
    var mail: String
) : IRequest