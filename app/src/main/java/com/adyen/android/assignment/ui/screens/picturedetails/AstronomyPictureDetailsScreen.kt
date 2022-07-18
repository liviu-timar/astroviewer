package com.adyen.android.assignment.ui.screens.picturedetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.adyen.android.assignment.ui.widgets.TextCustom

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

    pictureDetails?.let { details ->
        TextCustom(
            text = details.desc
        )
    }
}