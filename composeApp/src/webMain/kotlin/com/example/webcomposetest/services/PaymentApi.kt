package com.example.webcomposetest.services

import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse

class PaymentApi(private val httpClient: HttpClient) {
    suspend fun processPayment(token: String, amount: Double): HttpResponse {
        return httpClient.post("payment") {
            parameter("token", token)
            parameter("amount", amount)
        }
    }
}