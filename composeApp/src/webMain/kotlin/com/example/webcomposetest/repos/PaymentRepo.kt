package com.example.webcomposetest.repos

interface PaymentRepo {
    suspend fun processPayment(amount: Double, token: String): String
}