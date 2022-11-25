package com.magicpark.api.services

import com.google.firebase.auth.FirebaseAuth
import com.magicpark.api.model.database.User
import com.magicpark.api.model.request.authentification.LoginRequest
import com.magicpark.api.model.response.authentification.LoginResponse
import com.magicpark.api.repositories.IUserRepository
import com.magicpark.api.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class LoginService @Autowired constructor(
    var userRepository: IUserRepository
) {

    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok().body("ok")
    }
}