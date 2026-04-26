package com.example.webcomposetest.repos

import com.example.webcomposetest.models.Product
import com.example.webcomposetest.services.ProductsApi
import com.example.webcomposetest.utils.isSuccessful
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

class ProductsRepoImpl(
    private val productsApi: ProductsApi
) : ProductsRepo {
    override suspend fun getProducts(): List<Product> {
        val result = productsApi.getProducts()

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun getProduct(id: Long): Product {
        val result = productsApi.getProduct(id)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun addProduct(product: Product): String {
        val result = productsApi.addProduct(product)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun addProducts(products: List<Product>): String {
        val result = productsApi.addProducts(products)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun updateProduct(product: Product): String {
        val result = productsApi.updateProduct(product)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun deleteProduct(id: Long): String {
        val result = productsApi.deleteProduct(id)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }
}