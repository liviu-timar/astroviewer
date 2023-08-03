package com.liviutimar.astroviewer.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.liviutimar.astroviewer.ui.navigation.PictureRoutes
import com.liviutimar.astroviewer.ui.navigation.pictureNavGraph
import com.liviutimar.astroviewer.ui.theme.MainTheme
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
                    MainComposable()
                }
            }
        }
    }
}

@Composable
private fun MainComposable() {
    val navController = rememberNavController()
    var topBar by remember { mutableStateOf<AppBar>(null) }
    var bottomBar by remember { mutableStateOf<AppBar>(null) }

    Scaffold(
        topBar = { topBar?.invoke() },
        bottomBar = { bottomBar?.invoke() },
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = PictureRoutes.ROOT,
            modifier = Modifier.padding(padding)
        ) {
            pictureNavGraph(
                navController = navController,
                defineTopBar = { topBar = it },
                defineBottomBar = { bottomBar = it },
            )
        }
    }
}

typealias AppBar = (@Composable () -> Unit)?