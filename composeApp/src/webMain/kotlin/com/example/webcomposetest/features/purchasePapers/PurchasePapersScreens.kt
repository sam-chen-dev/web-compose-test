package com.example.webcomposetest.features.purchasePapers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.webcomposetest.utils.Image
import com.example.webcomposetest.utils.PriceOutputTransformation
import com.example.webcomposetest.utils.TonalIconButton
import com.example.webcomposetest.utils.showDecimal
import com.kttipay.payment.WebPaymentManager
import com.kttipay.payment.api.PaymentEnvironment
import com.kttipay.payment.api.PaymentLauncher
import com.kttipay.payment.api.PaymentResult
import com.kttipay.payment.api.config.GatewayConfig
import com.kttipay.payment.api.config.GooglePayAuthMethod
import com.kttipay.payment.api.config.GooglePayCardNetwork
import com.kttipay.payment.api.config.GooglePayConfig
import com.kttipay.payment.api.config.WebPaymentConfig
import com.kttipay.payment.ui.PaymentManagerProvider
import com.kttipay.payment.ui.launcher.rememberGooglePayWebLauncher
import com.kttipay.payment.ui.rememberWebPaymentManager
import compose.icons.TablerIcons
import compose.icons.tablericons.Minus
import compose.icons.tablericons.Plus
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import webcomposetest.composeapp.generated.resources.Res
import webcomposetest.composeapp.generated.resources.google_pay_buton
import webcomposetest.composeapp.generated.resources.google_pay_merchant_id
import webcomposetest.composeapp.generated.resources.merchant_name
import webcomposetest.composeapp.generated.resources.receipt_paper_product
import webcomposetest.composeapp.generated.resources.stripe_test_publishable_key

@Composable
fun PurchasePapersScreen(onNavigateToOrderConfirmationTrigger: (String) -> Unit) {
    val viewModel: PurchasePapersViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val totalState = viewModel.totalState
    val customerNameState = viewModel.customerNameState
    val customerEmailState = viewModel.customerEmailState
    val customerAddressState = viewModel.customerAddressState
    val webPaymentManager = rememberWebPaymentManager(createWebPaymentConfig())
    val onGooglePayClick: (PaymentLauncher) -> Unit = { googleyPayLauncher ->
        googleyPayLauncher.launch(totalState.text.toString().toDouble().showDecimal(2))
    }

    LaunchedEffect(Unit) {
        viewModel.isNavigateToOrderConfirmation.collectLatest { id ->
            onNavigateToOrderConfirmationTrigger(id)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Toolbar()

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(600.dp)
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    Res.drawable.receipt_paper_product,
                    "Product image",
                    ContentScale.Crop,
                    Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                )

                Spacer(Modifier.width(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(uiState.product.name, fontSize = 20.sp)
                    Text("Free shipping", color = MaterialTheme.colorScheme.primary, fontSize = 15.sp)

                    Spacer(Modifier.weight(1F))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Quantity:")
                        Spacer(Modifier.weight(1F))

                        TonalIconButton(TablerIcons.Minus, "Minus", uiState.onMinusClick)
                        Spacer(Modifier.width(8.dp))

                        Text(uiState.quantity.toString())
                        Spacer(Modifier.width(8.dp))

                        TonalIconButton(TablerIcons.Plus, "Plus", uiState.onPlusClick)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            PriceTextField(totalState, "Total")
            Spacer(Modifier.height(32.dp))

            HorizontalDivider()
            Spacer(Modifier.height(32.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Customer Info", fontSize = 20.sp)
                Spacer(Modifier.height(16.dp))

                InfoTextField(customerNameState, "Customer Name")
                Spacer(Modifier.height(16.dp))

                InfoTextField(customerEmailState, "Customer Email")
                Spacer(Modifier.height(16.dp))

                InfoTextField(customerAddressState, "Customer Address")
                Spacer(Modifier.height(32.dp))

                HorizontalDivider()
                Spacer(Modifier.height(32.dp))

                GooglePayButton(webPaymentManager, onGooglePayClick, uiState.onGooglePayTokenReceived)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar() {
    TopAppBar(
        title = { Text("Purchase Papers") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun PriceTextField(state: TextFieldState, label: String) {
    OutlinedTextField(
        state = state,
        label = { Text(label) },
        labelPosition = TextFieldLabelPosition.Attached(true),
        readOnly = true,
        outputTransformation = PriceOutputTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun InfoTextField(state: TextFieldState, label: String) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        state = state,
        label = { Text(label) },
        labelPosition = TextFieldLabelPosition.Attached(true),
        modifier = Modifier
            .fillMaxWidth()
            .onPreviewKeyEvent { event ->
                if (event.key == Key.Tab && event.type == KeyEventType.KeyDown) {
                    focusManager.moveFocus(FocusDirection.Next)

                    true
                } else {
                    false
                }
            }
    )
}

@Composable
private fun createWebPaymentConfig() = WebPaymentConfig(
    //environment = PaymentEnvironment.Production,
    environment = PaymentEnvironment.Development,
    googlePay = GooglePayConfig(
        merchantId = stringResource(Res.string.google_pay_merchant_id),
        merchantName = stringResource(Res.string.merchant_name),
        //gateway = GatewayConfig.Stripe(stringResource(Res.string.stripe_production_publishable_key))),
        gateway = GatewayConfig.Stripe(stringResource(Res.string.stripe_test_publishable_key)),
        allowedCardNetworks = setOf(
            GooglePayCardNetwork.VISA,
            GooglePayCardNetwork.MASTERCARD,
            GooglePayCardNetwork.AMEX,
            GooglePayCardNetwork.DISCOVER
        ),
        allowedAuthMethods = setOf(GooglePayAuthMethod.PAN_ONLY, GooglePayAuthMethod.CRYPTOGRAM_3DS),
        allowCreditCards = true,
        currencyCode = "USD",
        countryCode = "US"
    )
)

@Composable
private fun GooglePayButton(
    paymentManager: WebPaymentManager,
    onGooglePayClick: (PaymentLauncher) -> Unit,
    onGooglePayTokenReceived: (String) -> Unit
) {
    PaymentManagerProvider(paymentManager) {
        val googlePayLauncher = rememberGooglePayWebLauncher { result ->
            when (result) {
                is PaymentResult.Success -> onGooglePayTokenReceived(result.token)
                is PaymentResult.Error -> println("Error: ${result.reason.name}")
                is PaymentResult.Cancelled -> println("Payment Cancelled")
            }
        }

        Image(
            Res.drawable.google_pay_buton,
            "Google Pay",
            ContentScale.None,
            Modifier.clickable { onGooglePayClick(googlePayLauncher) }
        )
    }
}