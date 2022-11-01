package com.magicpark.api.controllers

import com.magicpark.api.model.request.authentification.LoginRequest
import com.magicpark.api.services.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/customer")
class CustomerController @Autowired constructor(val service: LoginService) {

    @PostMapping("/login")
    fun login(@RequestBody @Valid request: LoginRequest) : ResponseEntity<Any> = service.login(request)

}

