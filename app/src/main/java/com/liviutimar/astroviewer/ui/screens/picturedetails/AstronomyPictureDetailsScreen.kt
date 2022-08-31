package com.liviutimar.astroviewer.ui.screens.picturedetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.liviutimar.astroviewer.ui.screens.common.AstronomyImage
import com.liviutimar.astroviewer.ui.screens.common.CustomTopAppBar
import com.liviutimar.astroviewer.ui.screens.common.TextCustom
import com.liviutimar.astroviewer.ui.screens.common.TextCustomMedium
import com.liviutimar.astroviewer.ui.screens.picturedetails.models.PictureDetails

@Composable
fun AstronomyPictureDetailsScreen(
    viewModel: AstronomyPictureDetailsViewModel,
    navController: NavController,
    pictureId: Int,
) {
    if (pictureId != 0) {
        LaunchedEffect(key1 = Unit) {
            viewModel.getPictureDetails(pictureId)
        }
    }

    val pictureDetails by viewModel.pictureDetails.observeAsState()

    Box(modifier = Modifier.verticalScroll(rememberScrollState())) {
        pictureDetails?.let { details -> PictureDetails(details = details) } ?: TextCustom(text = "No data")

        CustomTopAppBar(
            isTransparent = true,
            onBackClick = { navController.popBackStack() }
        )
    }
}

@Composable
private fun PictureDetails(details: PictureDetails) {
    Column {
        Image(url = details.url)
        Column(modifier = Modifier.padding(all = 30.dp)) {
            Title(title = details.title)
            Spacer(modifier = Modifier.height(40.dp))
            Date(date = details.date)
            Spacer(modifier = Modifier.height(20.dp))
            Caption(desc = details.desc)
        }
    }
}

@Composable
private fun Image(url: String) {
    val blackColorPainter = remember { ColorPainter(color = Color.Black) }

    AstronomyImage(
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
private fun Caption(desc: String) {
    TextCustom(
        text = desc,
        fontSize = 14.sp,
        lineHeight = 23.sp
    )
}