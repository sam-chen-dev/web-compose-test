package com.example.webcomposetest.features.orderConfimation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webcomposetest.models.Order
import com.example.webcomposetest.repos.OrdersRepoImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OrderConfirmationViewModel(
    private val id: String,
    private val ordersRepo: OrdersRepoImpl
) : ViewModel() {
    private val uiScope = viewModelScope
    private val _uiState = MutableStateFlow(createUiState())

    val uiState = _uiState.asStateFlow()

    init {
        downloadOrder()
    }

    private fun createUiState() = OrderConfirmationUiState(
        null
    )

    private fun downloadOrder() = uiScope.launch {
        try {
            val order = ordersRepo.getOrder(id)

            updateOrder(order)
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
        }
    }

    private fun updateOrder(order: Order) = _uiState.update { it.copy(order = order) }
}