package com.example.webcomposetest.repos

import com.example.webcomposetest.models.PaymentRequest

interface PaymentRepo {
    suspend fun processPayment(request: PaymentRequest): String
}