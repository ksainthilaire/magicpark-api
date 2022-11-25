package com.magicpark.api.services

import com.google.firebase.auth.FirebaseAuth
import com.magicpark.api.model.database.User
import com.magicpark.api.model.request.authentification.LoginRequest
import com.magicpark.api.model.request.authentification.UpdateUserRequest
import com.magicpark.api.repositories.*
import com.magicpark.api.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
@Transactional
class UserService @Autowired constructor(
    var userRepository: IUserRepository
) {


    fun getAllUsers(): MutableIterable<User> = userRepository.findAll()

    fun getUserById(id: Long): User =
        userRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getUser(id: Long): ResponseEntity<Any> {
        val user = getUserById(id)
        return ResponseEntity.ok().body(user)
    }

    fun existsById(id: Long): Boolean = userRepository.existsById(id)


    fun login(request: LoginRequest): ResponseEntity<Any> {
        val defaultAuth = FirebaseAuth.getInstance();
        val user: User

        try {
            val firebaseToken = defaultAuth.verifyIdToken(request.token)

            val result = userRepository.findByMail(firebaseToken.email)
            user = if (result.isPresent) result.get()
            else {
                val userRecord = User(
                    fullName = firebaseToken.name,
                    mail = firebaseToken.email
                )
                userRepository.save(userRecord)
            }

            val accessToken: String = JwtUtil.generateAccessToken(user)
            user.token = accessToken
            userRepository.save(user)

            return ResponseEntity.ok().body(user)
        } catch (ex: Exception) {
            println(ex)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }
    }

    fun userEmailExists(email: String): Boolean = userRepository.findByMail(email).isPresent

    fun updateUser(id: Long, form: UpdateUserRequest): ResponseEntity<Any> {
        val user = userRepository.findById(id)
        return if (user.isPresent) {
            updateUser(user.get(), form)
            ResponseEntity.status(HttpStatus.OK).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    fun updateUser(user: User, form: UpdateUserRequest) {


        form.mail?.let { user.mail = it }
        form.phoneNumber?.let { user.phoneNumber = it }
        form.fullName?.let { user.fullName = it }
        form.country?.let { user.country = it }

        userRepository.save(user)
    }


    companion object {
        const val TOKEN_LENGTH: Int = 30
        const val TOKEN_LIFETIME: Int = 5
    }

}