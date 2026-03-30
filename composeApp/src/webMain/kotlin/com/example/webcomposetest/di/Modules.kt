package com.example.webcomposetest.di

import com.example.webcomposetest.utils.Animal
import org.koin.dsl.module

val appModule = module {
    single { Animal() }
}