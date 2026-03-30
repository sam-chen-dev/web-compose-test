package com.example.webcomposetest.features.studentDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.webcomposetest.utils.Animal
import org.koin.compose.koinInject

@Composable
fun StudentDetailScreen(
    onBackTrigger: () -> Unit
) {
    val animal: Animal = koinInject()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Toolbar(onBackTrigger)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            //Button("Go Back", onBackTrigger)
            Text("Animal: ${animal.name}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text("Student Detail") },
        //navigationIcon = { IconButton(TablerIcons.ArrowLeft, "Back", onBackClick) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}