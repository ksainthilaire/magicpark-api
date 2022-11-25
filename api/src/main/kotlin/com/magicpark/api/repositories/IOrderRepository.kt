package com.magicpark.api.repositories

import com.magicpark.api.model.database.Order
import com.magicpark.api.model.database.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface IOrderRepository : JpaRepository<Order, Long> {

    fun findByNumber(number: String): Optional<Order>

}