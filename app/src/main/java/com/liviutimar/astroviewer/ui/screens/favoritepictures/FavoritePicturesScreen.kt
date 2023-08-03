@file:OptIn(ExperimentalLifecycleComposeApi::class)

package com.liviutimar.astroviewer.ui.screens.favoritepictures

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.ui.AppBar
import com.liviutimar.astroviewer.ui.navigation.PictureRoutes
import com.liviutimar.astroviewer.ui.screens.common.CustomBottomNavigation
import com.liviutimar.astroviewer.ui.screens.common.CustomTopAppBar
import com.liviutimar.astroviewer.ui.screens.common.FullscreenMessage
import com.liviutimar.astroviewer.ui.screens.common.PictureList

@Composable
fun FavoritePicturesScreen(
    viewModel: FavoritePicturesViewModel,
    navController: NavController,
    defineTopBar: (AppBar) -> Unit,
    defineBottomBar: (AppBar) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { viewModel.getFavoritePictures() }

    defineAppBars(defineTopBar, defineBottomBar, navController)

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
        FullscreenMessage(
            icon = R.drawable.ic_favorite_filled_white,
            firstLine = R.string.no_favorites,
            secondLine = R.string.mark_pictures_as_favorites
        )
    }
}

private fun defineAppBars(
    defineTopBar: (AppBar) -> Unit,
    defineBottomBar: (AppBar) -> Unit,
    navController: NavController
) {
    defineTopBar {
        CustomTopAppBar(
            title = stringResource(id = R.string.favorites),
            onFetchClick = null,
            onSortClick = null
        )
    }
    defineBottomBar {
        CustomBottomNavigation(navController = navController)
    }
}