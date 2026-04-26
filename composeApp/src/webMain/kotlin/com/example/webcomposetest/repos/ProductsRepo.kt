package com.example.webcomposetest.repos

import com.example.webcomposetest.models.Product

interface ProductsRepo {
    suspend fun getProducts(): List<Product>

    suspend fun getProduct(id: Long): Product

    suspend fun addProduct(product: Product): String

    suspend fun addProducts(products: List<Product>): String

    suspend fun updateProduct(product: Product): String

    suspend fun deleteProduct(id: Long): String
}