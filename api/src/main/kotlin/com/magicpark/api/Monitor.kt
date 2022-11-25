package com.magicpark.api

import com.magicpark.api.payments.orange.Orange
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class Monitor {

    @Autowired
    private lateinit var api: OrangeApi

    @PostConstruct
    fun init() {
        api.getAccessToken().subscribe {
            Orange.getInstance().setAccessToken(it.accessToken!!)
        }
    }
}

