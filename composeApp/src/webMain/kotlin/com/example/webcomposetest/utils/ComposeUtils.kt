package com.example.webcomposetest.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import webcomposetest.composeapp.generated.resources.Res
import webcomposetest.composeapp.generated.resources.processing

@Composable
fun Text(
    res: StringResource,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(stringResource(res), color = color, fontSize = fontSize, modifier = modifier)
}

@Composable
fun Text(
    text: String,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(text, color = color, fontSize = fontSize, modifier = modifier)
}

@Composable
fun TextButton(res: StringResource, onClick: () -> Unit) {
    TextButton(onClick) {
        Text(res)
    }
}

@Composable
fun TextButton(text: String, onClick: () -> Unit) {
    TextButton(onClick) {
        Text(text)
    }
}

@Composable
fun Icon(
    imageVector: ImageVector,
    contentDescription: String,
    tint: Color = LocalContentColor.current,
    modifier: Modifier = Modifier
) {
    Icon(imageVector, contentDescription, tint = tint, modifier = modifier)
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
fun IconButton(
    res: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit,
    tint: Color = LocalContentColor.current
) {
    IconButton(onClick) {
        Icon(painterResource(res), contentDescription, tint = tint)
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

@Composable
fun Image(
    res: DrawableResource,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier = Modifier
) {
    Image(painterResource(res), contentDescription, contentScale = contentScale, modifier = modifier)
}

@Composable
fun ProcessingDialog() {
    Dialog({}) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterStart))
            Text(Res.string.processing, modifier = Modifier.align(Alignment.Center))
        }
    }
}

/*
@Composable
fun CoilImage(url: String, contentDescription: String, contentScale: ContentScale, modifier: Modifier = Modifier) {
    AsyncImage(url, contentDescription, contentScale = contentScale, modifier = modifier)
}*/