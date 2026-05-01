package com.example.webcomposetest.features.purchasePapers

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webcomposetest.enums.PaymentStatus
import com.example.webcomposetest.models.Order
import com.example.webcomposetest.models.PaymentRequest
import com.example.webcomposetest.models.Product
import com.example.webcomposetest.repos.OrdersRepoImpl
import com.example.webcomposetest.repos.PaymentRepoImpl
import com.example.webcomposetest.repos.ProductsRepoImpl
import com.example.webcomposetest.utils.RandomHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.time.Clock

class PurchasePapersViewModel(
    private val productsRepo: ProductsRepoImpl,
    private val ordersRepo: OrdersRepoImpl,
    private val paymentRepo: PaymentRepoImpl
) : ViewModel() {
    private val uiScope = viewModelScope
    private val _uiState = MutableStateFlow(createUiState())
    private val _isNavigateToOrderConfirmation = MutableSharedFlow<String>()

    val totalState = TextFieldState("0.00")
    val customerNameState = TextFieldState()
    val customerEmailState = TextFieldState()
    val customerAddressState = TextFieldState()

    val uiState = _uiState.asStateFlow()
    val isNavigateToOrderConfirmation = _isNavigateToOrderConfirmation.asSharedFlow()

    init {
        downloadProduct()
    }

    private fun createUiState() = PurchasePapersUiState(
        Product(),
        0,
        ::decreaseQuantity,
        ::increaseQuantity,
        ::processPayment
    )

    private fun increaseQuantity() {
        if (_uiState.value.quantity == uiState.value.product.stock) return

        updateQuantity(_uiState.value.quantity + 1)
        updateTotal(_uiState.value.quantity * _uiState.value.product.price)
    }

    private fun decreaseQuantity() {
        if (_uiState.value.quantity == 0) return

        updateQuantity(_uiState.value.quantity - 1)
        updateTotal(_uiState.value.quantity * _uiState.value.product.price)
    }

    private fun downloadProduct() = uiScope.launch {
        try {
            val product = productsRepo.getProducts().first()

            updateProduct(product)
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
        }
    }

    private fun processPayment(tokenJsonString: String) = uiScope.launch {
        try {
            val total = totalState.text.toString().toDouble()
            val json = Json.parseToJsonElement(tokenJsonString).jsonObject
            val token = json["id"]!!.jsonPrimitive.content
            val paymentRequest = PaymentRequest(token, total)
            val paymentId = paymentRepo.processPayment(paymentRequest)
            println("paymentId: $paymentId")
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
        }
    }

    fun saveOrder() = uiScope.launch {
        try {
            val order = Order(
                RandomHelper.generateRandomString(8),
                _uiState.value.product.id,
                _uiState.value.product.name,
                _uiState.value.quantity,
                _uiState.value.quantity * _uiState.value.product.price,
                totalState.text.toString().toDouble(),
                //0.00,
                customerNameState.text.toString(),
                customerEmailState.text.toString(),
                customerAddressState.text.toString(),
                Clock.System.now().toEpochMilliseconds(),
                "",
                PaymentStatus.SUCCEEDED.id
            )

            ordersRepo.addOrder(order)

            _isNavigateToOrderConfirmation.emit(order.id)
        } catch (e: Exception) {
            println("Error: ${e.message.toString()}")
        }
    }

    private fun updateProduct(product: Product) = _uiState.update { it.copy(product = product) }
    private fun updateQuantity(quantity: Int) = _uiState.update { it.copy(quantity = quantity) }
    private fun updateTotal(total: Double) = totalState.setTextAndPlaceCursorAtEnd(total.toString())
}