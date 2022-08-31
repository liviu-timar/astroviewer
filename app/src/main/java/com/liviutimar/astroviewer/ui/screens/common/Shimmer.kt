package com.liviutimar.astroviewer.ui.screens.common

import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun Shimmer(content: @Composable Brush.() -> Unit) {
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.4f),
        Color.LightGray.copy(alpha = 0.2f),
    )
    val gradientWidth = 300
    val brush = Brush.horizontalGradient(
        colors = shimmerColors,
        startX = translateAnimation.value - gradientWidth / 2,
        endX = translateAnimation.value + gradientWidth / 2
    )

    content(brush)
}