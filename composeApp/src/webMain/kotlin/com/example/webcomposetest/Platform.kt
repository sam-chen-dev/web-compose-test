package com.example.webcomposetest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform