package com.adyen.android.assignment.ui.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.adyen.android.assignment.ui.screens.picturedetails.AstronomyPictureDetailsScreen
import com.adyen.android.assignment.ui.screens.picturelist.AstronomyPictureListScreen

fun NavGraphBuilder.pictureNavGraph(navController: NavController) {
    navigation(startDestination = PictureRoutes.PictureList.ROUTE, route = PictureRoutes.ROOT) {
        composable(PictureRoutes.PictureList.ROUTE) {
            AstronomyPictureListScreen(viewModel = hiltViewModel(), navController = navController)
        }

        composable(PictureRoutes.PictureDetails.ROUTE) { backStackEntry ->
            backStackEntry.arguments.let { args ->
                with(PictureRoutes.PictureDetails) {
                    val pictureTitle = args?.getString(PARAM_PICTURE_TITLE)

                    AstronomyPictureDetailsScreen(pictureTitle = pictureTitle)
                }
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
        const val PARAM_PICTURE_TITLE = "pictureTitle"

        private const val BASE_ROUTE = "$ROOT/details"
        const val ROUTE = "$BASE_ROUTE/{$PARAM_PICTURE_TITLE}"

        fun createRouteWithArgs(pictureTitle: String) = "$BASE_ROUTE/$pictureTitle"
    }
}
