package com.example.webcomposetest.services

import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

class PaymentApi(private val httpClient: HttpClient) {
    suspend fun processPayment(amount: Double, token: String): HttpResponse {
        return httpClient.post("payment-web") {
            parameter("amount", amount)
            parameter("token", token)
        }
    }
}