package com.example.webcomposetest.utils

expect object DataStore {
    fun saveString(key: String, value: String)

    fun getString(key: String): String?
}