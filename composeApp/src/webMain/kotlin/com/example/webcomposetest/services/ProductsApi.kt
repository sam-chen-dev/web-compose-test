package com.example.webcomposetest.services

import com.example.webcomposetest.models.Product
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class ProductsApi(private val httpClient: HttpClient) {
    suspend fun getProducts(): HttpResponse {
        return httpClient.get("products")
    }

    suspend fun getProduct(id: Long): HttpResponse {
        return httpClient.get("products/$id")
    }

    suspend fun addProduct(product: Product): HttpResponse {
        return httpClient.post("products") {
            setBody(product)
        }
    }

    suspend fun addProducts(products: List<Product>): HttpResponse {
        return httpClient.post("products-batch") {
            setBody(products)
        }
    }

    suspend fun updateProduct(product: Product): HttpResponse {
        return httpClient.put("products") {
            setBody(product)
        }
    }

    suspend fun deleteProduct(id: Long): HttpResponse {
        return httpClient.delete("products/$id")
    }
}