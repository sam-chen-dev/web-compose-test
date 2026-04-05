package com.example.webcomposetest.services

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NyAcgGalaService {
    val httpClient = HttpClient {
        defaultRequest {
            header("x-api-key", "samchenceo0877")
            url("https://nyacggala-staging.com/")
        }

        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }
    }
}