package com.liviutimar.astroviewer.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liviutimar.astroviewer.ui.screens.picturedetails.AstronomyPictureDetailsScreen
import com.liviutimar.astroviewer.ui.screens.picturelist.AstronomyPictureListScreen

fun NavGraphBuilder.pictureNavGraph(navController: NavController) {
    navigation(startDestination = PictureRoutes.PictureList.ROUTE, route = PictureRoutes.ROOT) {
        composable(PictureRoutes.PictureList.ROUTE) {
            AstronomyPictureListScreen(
                viewModel = hiltViewModel(),
                navController = navController
            )
        }

        composable(PictureRoutes.PictureDetails.ROUTE) { backStackEntry ->
            backStackEntry.arguments.let { args ->
                val pictureId = args?.getString(PictureRoutes.PictureDetails.PARAM_PICTURE_ID)

                AstronomyPictureDetailsScreen(
                    viewModel = hiltViewModel(),
                    navController = navController,
                    pictureId = pictureId?.toInt() ?: 0
                )
            }
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
}
