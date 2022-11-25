package com.magicpark.api.repositories

import com.magicpark.api.model.database.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface IUserRepository : JpaRepository<User, Long> {

    fun findByMail(mail: String) : Optional<User>

    @Modifying
    @Query("Update User user SET user.fullName=:fullname WHERE user.id=:id")
    fun updateFullName(@Param("id") id: Long?, @Param("fullname") title: String?)

    @Modifying
    @Query("Update User user SET user.token=:token WHERE user.id=:id")
    fun updateToken(@Param("id") id: Long?, @Param("token") t: String?)

}
