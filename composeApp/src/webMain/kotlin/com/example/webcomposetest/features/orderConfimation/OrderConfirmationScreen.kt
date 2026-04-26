package com.example.webcomposetest.features.orderConfimation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.webcomposetest.utils.Icon
import com.example.webcomposetest.utils.TextButton
import com.example.webcomposetest.utils.showDecimal
import compose.icons.TablerIcons
import compose.icons.tablericons.CircleCheck
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun OrderConfirmationScreen(
    id: String,
    onBackToHomeClick: () -> Unit
) {
    val viewModel: OrderConfirmationViewModel = koinViewModel(parameters = { parametersOf(id) })
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Toolbar()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))

            Icon(
                TablerIcons.CircleCheck,
                "Check",
                MaterialTheme.colorScheme.primary,
                Modifier.size(200.dp)
            )

            Spacer(Modifier.height(32.dp))

            Text("Order Confirmed!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(32.dp))

            Column(
                modifier = Modifier.width(400.dp)
            ) {
                OrderInfoText("Order ID", uiState.order?.id ?: "")
                OrderInfoText("Order Placed", uiState.order?.getFormattedCreationTime() ?: "")
                Spacer(Modifier.height(16.dp))

                OrderInfoText(
                    "${uiState.order?.productName ?: ""} x ${uiState.order?.productQuantity ?: 0}",
                    "$${uiState.order?.productTotalPrice?.showDecimal(2) ?: 0.00}"
                )

                OrderInfoText("Shipping fee", "$0.00")
                OrderInfoText("Total", "$${uiState.order?.totalPrice?.showDecimal(2) ?: 0.00}")
                Spacer(Modifier.height(16.dp))

                CustomerInfoText("[Customer Name]", uiState.order?.customerName ?: "")
                Spacer(Modifier.height(8.dp))

                CustomerInfoText("[Customer Email]", uiState.order?.customerEmail ?: "")
                Spacer(Modifier.height(8.dp))

                CustomerInfoText("[Customer Address]", uiState.order?.customerAddress ?: "")
                Spacer(Modifier.height(16.dp))

                HorizontalDivider()
                Spacer(Modifier.height(16.dp))
            }

            Column(
                modifier = Modifier.width(400.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Thank you so much for your purchase! Items will be shipped within 3 days (with email notification).",
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                Text("You can find this order in [Order History].", color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(16.dp))

                TextButton("Back to Home", onBackToHomeClick)
            }

            Spacer(Modifier.height(56.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(
        title = { Text("Order Confirmation") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun OrderInfoText(label: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)

        Spacer(Modifier.weight(1F))

        Text(description)
    }
}

@Composable
private fun CustomerInfoText(label: String, description: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label)
        Text(description)
    }
}