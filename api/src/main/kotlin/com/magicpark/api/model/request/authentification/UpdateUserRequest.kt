package com.magicpark.api.model.request.authentification

import com.fasterxml.jackson.annotation.JsonAlias
import com.magicpark.api.model.request.base.IRequest
import javax.validation.constraints.Email




data class UpdateUserRequest(
    @Email
    @JsonAlias("mail")
    val mail: String? = null,

    @JsonAlias("fullname")
    val fullName: String? = null,

    @JsonAlias("phone_number")
    val phoneNumber: String? = null,

    @JsonAlias("country")
    val country: String? = null
) : IRequest