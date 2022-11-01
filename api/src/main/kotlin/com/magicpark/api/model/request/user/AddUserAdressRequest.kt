package com.magicpark.api.model.request

import com.magicpark.api.model.database.UserAddress
import com.magicpark.api.model.request.base.IRequest

data class AddUserAddressRequest(
    var address: UserAddress
) : IRequest