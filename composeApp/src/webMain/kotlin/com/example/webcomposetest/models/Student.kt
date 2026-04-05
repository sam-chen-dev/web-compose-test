package com.example.webcomposetest.models

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Long,
    val name: String
)