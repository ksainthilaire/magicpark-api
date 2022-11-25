package com.magicpark.api.controllers

import com.magicpark.api.model.database.ShopItem
import com.magicpark.api.services.ShopService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/support")
class SupportController @Autowired constructor(val service: ShopService) {

    @GetMapping("")
    fun getAllShopItems() = service.getAllShopItems()

    @GetMapping("/{id}")
    fun getShopItem(@PathVariable id: Long) = service.getShopItemById(id)

    @PutMapping("/{id}")
    fun updateShopItem(
        @PathVariable id: Long, @RequestBody ShopItem: ShopItem
    ) = service.updateShopItem(id, ShopItem)
}

