package com.liviutimar.astroviewer.ui.screens.picturedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.ui.screens.common.CustomTopAppBar
import com.liviutimar.astroviewer.ui.screens.common.FullscreenMessage
import com.liviutimar.astroviewer.ui.screens.common.NetworkImage
import com.liviutimar.astroviewer.ui.screens.common.TextCustom
import com.liviutimar.astroviewer.ui.screens.common.TextCustomMedium

@Composable
fun PictureDetailsScreen(
    viewModel: PictureDetailsViewModel,
    navController: NavController,
    pictureId: Int,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (pictureId != 0) {
        LaunchedEffect(key1 = Unit) { viewModel.getPictureDetails(pictureId) }

        Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
            with(uiState) {
                PictureDetails(
                    title = title,
                    desc = desc,
                    date = date,
                    url = url,
                    isFavorite = isFavorite,
                    toggleFavoriteStatus = { viewModel.toggleFavoriteStatus(pictureId) }
                )
            }

            CustomTopAppBar(
                isTransparent = true,
                onBackClick = { navController.popBackStack() }
            )
        }
    } else {
        FullscreenMessage(
            icon = R.drawable.ic_error,
            firstLine = R.string.cannot_display_picture,
            secondLine = R.string.select_another_picture,
        )
    }
}

@Composable
private fun PictureDetails(
    title: String,
    desc: String,
    date: String,
    url: String,
    isFavorite: Boolean,
    toggleFavoriteStatus: () -> Unit,
) {
    Column {
        Image(url = url)
        Column(modifier = Modifier.padding(all = 30.dp)) {
            Title(title = title)
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Date(date = date)
                FavoriteStatus(
                    isFavorite = isFavorite,
                    toggleFavoriteStatus = toggleFavoriteStatus
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Description(desc = desc)
        }
    }
}

@Composable
private fun Image(url: String) {
    val blackColorPainter = remember { ColorPainter(color = Color.Black) }

    NetworkImage(
        url = url,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        crossfadeMillis = 200,
        error = blackColorPainter,
        fallback = blackColorPainter,
        placeholder = blackColorPainter
    )
}

@Composable
private fun Title(title: String) {
    TextCustomMedium(
        text = title,
        fontSize = 26.sp,
        lineHeight = 38.sp
    )
}

@Composable
private fun Date(date: String) {
    TextCustom(text = date)
}

@Composable
private fun FavoriteStatus(isFavorite: Boolean, toggleFavoriteStatus: () -> Unit) {
    Image(
        painter = painterResource(
            id = if (isFavorite) R.drawable.ic_favorite_filled_red else R.drawable.ic_favorite
        ),
        contentDescription = null,
        modifier = Modifier.clickable(
            onClick = toggleFavoriteStatus,
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        )
    )
}

@Composable
private fun Description(desc: String) {
    TextCustom(
        text = desc,
        fontSize = 14.sp,
        lineHeight = 23.sp
    )
}