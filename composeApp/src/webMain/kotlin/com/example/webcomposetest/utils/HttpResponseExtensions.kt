package com.example.webcomposetest.utils

import io.ktor.client.statement.HttpResponse

fun HttpResponse.isSuccessful() = status.value == 200