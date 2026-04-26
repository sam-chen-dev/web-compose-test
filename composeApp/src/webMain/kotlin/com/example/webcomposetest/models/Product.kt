package com.example.webcomposetest.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val stock: Int = 0
)