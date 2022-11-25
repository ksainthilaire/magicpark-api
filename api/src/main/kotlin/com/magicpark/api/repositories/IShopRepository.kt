package com.magicpark.api.repositories

import com.magicpark.api.model.database.Order
import com.magicpark.api.model.database.ShopItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IShopRepository : JpaRepository<ShopItem, Long>