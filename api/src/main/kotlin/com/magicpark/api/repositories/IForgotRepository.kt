package com.magicpark.api.repositories

import com.magicpark.api.model.database.UserForgot
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IForgotRepository : JpaRepository<UserForgot, Long>