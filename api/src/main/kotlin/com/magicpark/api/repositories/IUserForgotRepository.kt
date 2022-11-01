package com.magicpark.api.repositories

import com.magicpark.api.model.database.UserForgot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.sql.Timestamp
import java.util.*

interface IUserForgotRepository : JpaRepository<UserForgot, Long> {
    fun findByToken(token: String) : Optional<UserForgot>

    @Modifying
    @Query("Update UserForgot forgot SET forgot.token=:token, forgot.expiresAt=:expires_at WHERE forgot.id=:id")
    fun updateToken(
        @Param("id") id: Long?, @Param("token") token: String?,
        @Param("expires_at") expiresAt: Timestamp?
    )
}