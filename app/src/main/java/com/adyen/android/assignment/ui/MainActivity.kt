package com.adyen.android.assignment.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.adyen.android.assignment.ui.picturelist.AstronomyPictureListScreen
import com.adyen.android.assignment.ui.picturelist.AstronomyPictureListViewModel
import com.adyen.android.assignment.ui.theme.MainTheme
import com.adyen.android.assignment.ui.theme.NoRippleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AstronomyPictureListViewModel by viewModels()

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
                        AstronomyPictureListScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}