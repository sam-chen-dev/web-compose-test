package com.example.webcomposetest.models

import com.example.webcomposetest.utils.DateTimeFormat
import com.example.webcomposetest.utils.toFormattedDateTimeString
import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String,
    val productId: Long,
    val productName: String,
    val productQuantity: Int,
    val productTotalPrice: Double,
    val totalPrice: Double,
    val customerName: String,
    val customerEmail: String,
    val customerAddress: String,
    val creationTimeInMillis: Long,
    val paymentId: String,
    val paymentStatusId: Int
) {
    fun getFormattedCreationTime(): String {
        return creationTimeInMillis.toFormattedDateTimeString(DateTimeFormat.MM_DD_YYYY_HH_MM_SS_SLASH)
    }
}