package com.example.webcomposetest.repos

import com.example.webcomposetest.models.Order

interface OrdersRepo {
    suspend fun getOrders(): List<Order>

    suspend fun getOrder(id: String): Order

    suspend fun addOrder(order: Order): String

    suspend fun addOrders(orders: List<Order>): String

    suspend fun updateOrder(order: Order): String

    suspend fun deleteOrder(id: Long): String
}