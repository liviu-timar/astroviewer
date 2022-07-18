package com.adyen.android.assignment.ui.screens.picturelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPictureList(brush: Brush) {
    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp)) {
        ShimmerPictureRow(brush = brush)
        Spacer(modifier = Modifier.height(30.dp))
        ShimmerPictureRow(brush = brush)
        Spacer(modifier = Modifier.height(26.dp))
        ShimmerPictureRow(brush = brush)
    }
}

@Composable
private fun ShimmerPictureRow(brush: Brush) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        ShimmerImage(brush = brush)
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            ShimmerText(width = 250.dp, height = 16.dp, brush = brush)
            Spacer(modifier = Modifier.height(7.dp))
            ShimmerText(width = 60.dp, height = 14.dp, brush = brush)
        }
    }
}

@Composable
private fun ShimmerImage(brush: Brush) {
    Spacer(
        modifier = Modifier
            .size(40.dp)
            .clip(shape = CircleShape)
            .background(brush = brush)
    )
}

@Composable
private fun ShimmerText(width: Dp, height: Dp, brush: Brush) {
    Spacer(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(shape = RoundedCornerShape(size = 5.dp))
            .background(brush = brush)
    )
}