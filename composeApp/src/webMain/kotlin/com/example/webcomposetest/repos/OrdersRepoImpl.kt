package com.example.webcomposetest.repos

import com.example.webcomposetest.models.Order
import com.example.webcomposetest.services.OrdersApi
import com.example.webcomposetest.utils.isSuccessful
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

class OrdersRepoImpl(
    private val ordersApi: OrdersApi
) : OrdersRepo {
    override suspend fun getOrders(): List<Order> {
        val result = ordersApi.getOrders()

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun getOrder(id: String): Order {
        val result = ordersApi.getOrder(id)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun addOrder(order: Order): String {
        val result = ordersApi.addOrder(order)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun addOrders(orders: List<Order>): String {
        val result = ordersApi.addOrders(orders)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun updateOrder(order: Order): String {
        val result = ordersApi.updateOrder(order)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }

    override suspend fun deleteOrder(id: Long): String {
        val result = ordersApi.deleteOrder(id)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }
}