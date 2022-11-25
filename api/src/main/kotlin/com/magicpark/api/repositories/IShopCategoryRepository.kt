package com.magicpark.api.repositories

import com.magicpark.api.model.database.ShopCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IShopCategoryRepository : JpaRepository<ShopCategory, Long>