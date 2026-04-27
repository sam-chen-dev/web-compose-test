package com.example.webcomposetest.repos

interface PaymentRepo {
    suspend fun processPayment(token: String, amount: Double): String
}