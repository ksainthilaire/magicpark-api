package com.magicpark.api.controllers

import com.magicpark.api.model.database.Product
import com.magicpark.api.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/order")
class OrderController @Autowired constructor(val service: ProductService) {

    @GetMapping("")
    fun getAllProducts() = service.getAllProducts()

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long) = service.getProductById(id)

    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long, @RequestBody product: Product
    ) = service.updateProduct(id, product)
}

