package com.example.webcomposetest.features.purchasePapers

import com.example.webcomposetest.models.Product

data class PurchasePapersUiState(
    val product: Product,
    val quantity: Int,
    val onMinusClick: () -> Unit,
    val onPlusClick: () -> Unit,
    val onGooglePayTokenReceived: (String) -> Unit
)