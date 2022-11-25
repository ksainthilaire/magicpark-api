package com.magicpark.api.model.response.authentification

import com.fasterxml.jackson.annotation.JsonAlias
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class LoginResponse (

    @NotNull @Email  @Size(min = 5, max = 50)
    var mail: String? = null,

    @NotNull
    @JsonAlias("access_token")
    var accessToken: String? = null

)