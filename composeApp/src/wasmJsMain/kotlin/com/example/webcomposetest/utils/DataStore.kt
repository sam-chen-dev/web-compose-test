package com.example.webcomposetest.utils

import kotlinx.browser.window

actual object DataStore {
    actual fun saveString(key: String, value: String) {
        window.localStorage.setItem(key, value)
    }

    actual fun getString(key: String): String? {
        return window.localStorage.getItem(key)
    }
}