package com.adyen.android.assignment.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adyen.android.assignment.R
import com.adyen.android.assignment.ui.theme.BackgroundColor

@Composable
fun CustomTopAppBar(title: String, onBackClick: (() -> Unit)? = null) {
    TopAppBar(
        backgroundColor = BackgroundColor,
        elevation = 0.dp,
        contentPadding = PaddingValues(vertical = 5.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            onBackClick?.let {
                ClickableIcon(
                    imageResId = R.drawable.ic_arrow_left,
                    imageWidth = 10.dp,
                    alignment = Alignment.CenterStart,
                    onClick = onBackClick
                )
            }
            TextCustom(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 20.sp
            )
            ClickableIcon(
                imageResId = R.drawable.ic_reorder,
                imageWidth = 25.dp,
                alignment = Alignment.CenterEnd,
                onClick = {}
            )
        }
    }
}

@Composable
private fun BoxScope.ClickableIcon(
    @DrawableRes imageResId: Int,
    imageWidth: Dp,
    alignment: Alignment,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(48.dp)
            .align(alignment)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = stringResource(R.string.go_back),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(imageWidth)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTopAppBar() {
    CustomTopAppBar(
        title = stringResource(id = R.string.our_universe),
        onBackClick = {}
    )
}