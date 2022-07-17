package com.adyen.android.assignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.adyen.android.assignment.ui.navigation.PictureRoutes
import com.adyen.android.assignment.ui.navigation.pictureNavGraph
import com.adyen.android.assignment.ui.theme.MainTheme
import com.adyen.android.assignment.ui.theme.NoRippleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Use CompositionLocal to disable the ripple effect down the Composition tree
                    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
                        MainComposable()
                    }
                }
            }
        }
    }
}

@Composable
private fun MainComposable() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PictureRoutes.ROOT
    ) {
        pictureNavGraph(navController)
    }
}