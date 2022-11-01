package com.magicpark.api.controllers

import com.magicpark.api.model.request.AddUserAddressRequest
import com.magicpark.api.model.request.authentification.RegisterRequest
import com.magicpark.api.model.database.User
import com.magicpark.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserController @Autowired constructor(val service: UserService) {

    @GetMapping("")
    fun getAllUsers() = service.getAllUsers()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = service.getUserById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveUser(@RequestBody form: RegisterRequest): User = service.createUser(form)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long, @RequestBody User: User
    ) = service.updateUser(id, User)

    @PostMapping("/{id}/address")
    fun addUserAddress(@PathVariable id: Long, @RequestBody address: AddUserAddressRequest) = service.addUserAddress(id, address)
}