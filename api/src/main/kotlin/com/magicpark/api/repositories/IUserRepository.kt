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
    @Query("Update User user SET user.firstName=:firstname WHERE user.id=:id")
    fun updateFirstname(@Param("id") id: Long?, @Param("firstname") title: String?)

    @Modifying
    @Query("Update User user SET user.firstName=:lastname WHERE user.id=:id")
    fun updateLastname(@Param("id") id: Long?, @Param("lastname") title: String?)

    @Modifying
    @Query("Update User user SET user.password=:password WHERE user.id=:id")
    fun updatePassword(@Param("id") id: Long?, @Param("password") t: String?)

}
