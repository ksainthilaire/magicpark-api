package com.magicpark.api.repositories

import com.magicpark.api.model.database.User
import com.magicpark.api.model.database.UserTicket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface IUserTicketRepository : JpaRepository<UserTicket, Long> {

    fun findByUserId(userId: Long) : Optional<UserTicket>

}
