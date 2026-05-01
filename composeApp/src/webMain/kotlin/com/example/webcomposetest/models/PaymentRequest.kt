package com.example.webcomposetest.models

import kotlinx.serialization.Serializable

@Serializable
data class PaymentRequest(
    val token: String,
    val amount: Double
)