package com.example.webcomposetest.enums

enum class PaymentStatus(val id: Int) {
    SUCCEEDED(1),
    FAILED(2),
    REFUNDED(3)
}