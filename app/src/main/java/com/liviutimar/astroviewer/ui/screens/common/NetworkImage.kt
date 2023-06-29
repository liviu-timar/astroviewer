package com.liviutimar.astroviewer.ui.screens.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    crossfadeMillis: Int = 0,
    alpha: Float = 1f,
    error: Painter? = null,
    fallback: Painter? = null,
    placeholder: Painter? = null,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .size(Size.ORIGINAL)
            .crossfade(durationMillis = crossfadeMillis)
            .build(),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alpha = alpha,
        error = error,
        fallback = fallback,
        placeholder = placeholder
    )
}