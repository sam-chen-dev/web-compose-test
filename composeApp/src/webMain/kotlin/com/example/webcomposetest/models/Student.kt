package com.example.webcomposetest.models

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Long = 0L,
    val name: String = "",
    val genderId: Int = 0
)