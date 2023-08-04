package com.liviutimar.astroviewer.ui.screens.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.liviutimar.astroviewer.R
import com.liviutimar.astroviewer.ui.navigation.PictureRoutes
import com.liviutimar.astroviewer.ui.theme.BackgroundSecondary
import com.liviutimar.astroviewer.ui.theme.NoRippleTheme

@Composable
fun CustomBottomNavigation(navController: NavController) {
    val destinations = listOf(
        BottomNavDestination.Home,
        BottomNavDestination.Favorites
    )

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        BottomNavigation {
            val currentDestination = navController.currentBackStackEntryAsState().value?.destination

            destinations.forEach { dest ->
                BottomNavigationItem(
                    label = { Text(text = stringResource(id = dest.labelResourceId)) },
                    icon = {
                        Icon(
                            painter = painterResource(id = dest.iconResourceId),
                            contentDescription = null
                        )
                    },
                    selected = dest.route == currentDestination?.route,
                    onClick = {
                        if (dest.route != currentDestination?.route) {
                            navController.navigate(dest.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as user selects items
                                popUpTo(navController.graph.findStartDestination().id)
                                // Avoid multiple copies of the same destination when
                                // re-selecting the same item
                                launchSingleTop = true
                            }
                        }
                    },
                    modifier = Modifier.background(color = BackgroundSecondary)
                )
            }
        }
    }
}

sealed class BottomNavDestination(
    val route: String,
    @StringRes val labelResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    object Home : BottomNavDestination(PictureRoutes.PictureList.ROUTE, R.string.home, R.drawable.ic_dust)
    object Favorites : BottomNavDestination(PictureRoutes.FavoritePictures.ROUTE, R.string.favorites, R.drawable.ic_favorite_filled_white)
}