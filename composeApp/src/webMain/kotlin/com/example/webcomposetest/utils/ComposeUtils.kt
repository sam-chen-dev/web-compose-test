package com.example.webcomposetest.utils

import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit

@Composable
fun Text(text: String, color: Color = Color.Unspecified, fontSize: TextUnit = TextUnit.Unspecified) {
    Text(text, color = color, fontSize = fontSize)
}

@Composable
fun IconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = LocalContentColor.current
) {
    IconButton(onClick) {
        Icon(imageVector, contentDescription, tint = tint)
    }
}

@Composable
fun IconButton(text: String, onClick: () -> Unit) {
    IconButton(onClick) {
        Text(text)
    }
}

@Composable
fun FilledIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = LocalContentColor.current
) {
    FilledIconButton(onClick) {
        Icon(imageVector, contentDescription, tint = tint)
    }
}

@Composable
fun OutlinedIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = LocalContentColor.current
) {
    OutlinedIconButton(onClick) {
        Icon(imageVector, contentDescription, tint = tint)
    }
}

@Composable
fun TonalIconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = LocalContentColor.current
) {
    FilledTonalIconButton(onClick) {
        Icon(imageVector, contentDescription, tint = tint)
    }
}

@Composable
fun Button(text: String, onClick: () -> Unit) {
    Button(onClick) {
        Text(text)
    }
}

/*
@Composable
fun CoilImage(url: String, contentDescription: String, contentScale: ContentScale, modifier: Modifier = Modifier) {
    AsyncImage(url, contentDescription, contentScale = contentScale, modifier = modifier)
}*/
