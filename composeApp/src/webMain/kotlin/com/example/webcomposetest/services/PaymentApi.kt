package com.example.webcomposetest.services

import com.example.webcomposetest.models.PaymentRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class PaymentApi(private val httpClient: HttpClient) {
    suspend fun processPayment(request: PaymentRequest): HttpResponse {
        return httpClient.post("payment") {
            setBody(request)
        }
    }
}