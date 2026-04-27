package com.example.webcomposetest.repos

import com.example.webcomposetest.services.PaymentApi
import com.example.webcomposetest.utils.isSuccessful
import io.ktor.client.call.body
import io.ktor.client.statement.bodyAsText

class PaymentRepoImpl(
    private val paymentApi: PaymentApi
) : PaymentRepo {
    override suspend fun processPayment(amount: Double, token: String): String {
        val result = paymentApi.processPayment(amount, token)

        if (!result.isSuccessful()) {
            throw Exception(result.bodyAsText())
        }

        return result.body()
    }
}