package com.magicpark.api.repositories

import com.magicpark.api.model.database.UserDeletion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.sql.Timestamp
import java.util.*

interface IUserDeletionRepository : JpaRepository<UserDeletion, Long> {
    fun findByToken(token: String) : Optional<UserDeletion>

    @Modifying
    @Query("Update UserDeletion deletion SET deletion.token=:token, deletion.expiresAt=:expires_at WHERE deletion.id=:id")
    fun updateToken(
        @Param("id") id: Long?, @Param("token") token: String?,
        @Param("expires_at") expiresAt: Timestamp?
    )
}