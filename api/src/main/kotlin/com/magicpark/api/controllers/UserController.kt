package com.magicpark.api.controllers

import com.magicpark.api.model.request.authentification.UpdateUserRequest
import com.magicpark.api.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")

class UserController @Autowired constructor(val service: UserService) {

    @GetMapping("")
    fun getAllUsers() = service.getAllUsers()

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = service.getUserById(id)

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long, @RequestBody User: UpdateUserRequest
    ) = service.updateUser(id, User)

}
