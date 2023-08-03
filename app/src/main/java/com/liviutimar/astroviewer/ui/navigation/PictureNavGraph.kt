package com.liviutimar.astroviewer.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liviutimar.astroviewer.ui.screens.favoritepictures.FavoritePicturesScreen
import com.liviutimar.astroviewer.ui.screens.picturedetails.PictureDetailsScreen
import com.liviutimar.astroviewer.ui.screens.picturelist.PictureListScreen

fun NavGraphBuilder.pictureNavGraph(
    navController: NavController,
    setupBottomBar: (isVisible: Boolean) -> Unit
) {
    navigation(startDestination = PictureRoutes.PictureList.ROUTE, route = PictureRoutes.ROOT) {
        composable(PictureRoutes.PictureList.ROUTE) {
            setupBottomBar(true)
            PictureListScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(PictureRoutes.PictureDetails.ROUTE) { backStackEntry ->
            setupBottomBar(false)
            backStackEntry.arguments.let { args ->
                val pictureId = args?.getString(PictureRoutes.PictureDetails.PARAM_PICTURE_ID)

                PictureDetailsScreen(
                    viewModel = hiltViewModel(),
                    navController = navController,
                    pictureId = pictureId?.toInt() ?: 0
                )
            }
        }

        composable(PictureRoutes.FavoritePictures.ROUTE) {
            setupBottomBar(true)
            FavoritePicturesScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }
    }
}

object PictureRoutes {

    const val ROOT = "pictures"

    object PictureList {
        const val ROUTE = "$ROOT/list"
    }

    object PictureDetails {
        const val PARAM_PICTURE_ID = "pictureId"

        private const val BASE_ROUTE = "$ROOT/details"
        const val ROUTE = "$BASE_ROUTE/{$PARAM_PICTURE_ID}"

        fun createRouteWithArgs(pictureId: Int) = "$BASE_ROUTE/$pictureId"
    }

    object FavoritePictures {
        const val ROUTE = "$ROOT/favorites"
    }
}
