package com.magicpark.api.model.request.logs

import com.magicpark.api.model.request.base.IRequest

data class NotifyEventRequest(
    var syslog: String
) : IRequest