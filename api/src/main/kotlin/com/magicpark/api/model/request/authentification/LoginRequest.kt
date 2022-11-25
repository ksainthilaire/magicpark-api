package com.magicpark.api.model.request.authentification

import com.magicpark.api.model.request.base.IRequest
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class LoginRequest(

    @Size(min = 5, max = 50)
    var token: String? = null
) : IRequest