package com.example.webcomposetest.di

import com.example.webcomposetest.utils.Animal
import org.koin.dsl.KoinConfiguration
import org.koin.dsl.module

val koinConfiguration = KoinConfiguration {
    modules(
        module {
            single { Animal() }
        }
    )
}