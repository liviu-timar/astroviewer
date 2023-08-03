@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.liviutimar.astroviewer.ui.screens.favoritepictures

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.ui.navigation.PictureRoutes
import com.liviutimar.astroviewer.ui.screens.common.CustomTopAppBar
import com.liviutimar.astroviewer.ui.screens.common.PictureList
import com.liviutimar.astroviewer.ui.screens.common.TextCustomMedium

@Composable
fun FavoritePicturesScreen(viewModel: FavoritePicturesViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { viewModel.getFavoritePictures() }

    Column {
        CustomTopAppBar(title = stringResource(id = R.string.favorites))

        if (uiState.pictures.isNotEmpty()) {
            PictureList(
                pictures = uiState.pictures,
                onRowClick = { pictureId ->
                    navController.navigate(
                        PictureRoutes.PictureDetails.createRouteWithArgs(pictureId = pictureId)
                    )
                }
            )
        } else {
            NoFavorites()
        }
    }
}

@Composable
private fun NoFavorites() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextCustomMedium(
            text = stringResource(id = R.string.no_favorites),
            fontSize = 20.sp
        )
    }
}