package com.magicpark.api.repositories

import com.magicpark.api.model.database.UserAddress
import org.springframework.data.jpa.repository.JpaRepository

interface IUserAddressRepository : JpaRepository<UserAddress, Long>