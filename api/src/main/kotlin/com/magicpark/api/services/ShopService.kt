package com.magicpark.api.services
import com.magicpark.api.model.database.ShopCategory
import com.magicpark.api.model.database.ShopItem
import com.magicpark.api.repositories.IShopCategoryRepository
import com.magicpark.api.repositories.IShopRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ShopService(val shopRepository: IShopRepository,
val shopCategoryRepository: IShopCategoryRepository
) {

    fun getAllShopItems(): List<ShopItem> = shopRepository.findAll()

    fun getShopItemById(id: Long): ShopItem = shopRepository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun updateShopItem(id: Long, Shop: ShopItem) {
        if (shopRepository.existsById(id)) {
            Shop.id = id
            shopRepository.save(Shop)
        }
    }

    fun getShopCategories(): List<ShopCategory> = shopCategoryRepository.findAll()

}