package com.liviutimar.astroviewer.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.liviutimar.astroviewer.ui.screens.community.CommunityHomeScreen

fun NavGraphBuilder.communityNavGraph(
    navController: NavController,
    setupBottomBar: (isVisible: Boolean) -> Unit
) {
    navigation(startDestination = CommunityRoutes.CommunityHome.ROUTE, route = CommunityRoutes.ROOT) {
        composable(CommunityRoutes.CommunityHome.ROUTE) {
            setupBottomBar(true)
            CommunityHomeScreen()
        }
    }
}

object CommunityRoutes {

    const val ROOT = "community"

    object CommunityHome {
        const val ROUTE = "$ROOT/home"
    }
}