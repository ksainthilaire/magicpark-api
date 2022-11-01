package com.magicpark.api.repositories

import com.magicpark.api.model.database.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IOrderRepository : JpaRepository<Order, Long>