package com.example.webcomposetest.services

import com.example.webcomposetest.models.Order
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class OrdersApi(private val httpClient: HttpClient) {
    suspend fun getOrders(): HttpResponse {
        return httpClient.get("orders")
    }

    suspend fun getOrder(id: String): HttpResponse {
        return httpClient.get("orders/$id")
    }

    suspend fun addOrder(order: Order): HttpResponse {
        return httpClient.post("orders") {
            setBody(order)
        }
    }

    suspend fun addOrders(orders: List<Order>): HttpResponse {
        return httpClient.post("orders-batch") {
            setBody(orders)
        }
    }

    suspend fun updateOrder(order: Order): HttpResponse {
        return httpClient.put("orders") {
            setBody(order)
        }
    }

    suspend fun deleteOrder(id: Long): HttpResponse {
        return httpClient.delete("orders/$id")
    }
}