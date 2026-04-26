package com.example.webcomposetest.utils

import kotlin.math.pow
import kotlin.math.round

fun Double.showDecimal(digit: Int): String {
    val factor = 10.0.pow(digit)
    val rounded = round(this * factor) / factor

    return rounded.toString().let {
        if (!it.contains(".")) {
            it + "." + "0".repeat(digit)
        } else {
            it.padEnd(it.indexOf('.') + digit + 1, '0')
        }
    }
}