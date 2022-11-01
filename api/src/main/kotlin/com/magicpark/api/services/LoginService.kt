package com.magicpark.api.services

import com.magicpark.api.model.request.authentification.LoginRequest
import com.magicpark.api.model.response.authentification.LoginResponse
import com.magicpark.api.repositories.IUserRepository
import com.magicpark.api.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class LoginService @Autowired constructor(
    var userRepository: IUserRepository
) {

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    fun login(request: LoginRequest) : ResponseEntity<Any> {
        try {
            val result = userRepository.findByMail(request.mail!!)
            if (result.isPresent) {

                val user = result.get()

                if (!passwordEncoder.matches(request.password, user.password)) {
                    throw BadCredentialsException("User not found")
                }

                val accessToken: String = jwtUtil.generateAccessToken(user)
                val response = LoginResponse(user.mail, accessToken)

                return ResponseEntity.ok().body(response)
            } else {
                throw BadCredentialsException("User not found")
            }


        } catch (ex: BadCredentialsException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

}