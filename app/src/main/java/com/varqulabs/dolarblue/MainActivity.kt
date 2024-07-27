package com.varqulabs.dolarblue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.varqulabs.dolarblue.core.presentation.desingsystem.DolarBlueTheme
import com.varqulabs.dolarblue.navigation.AppNavGraph
import com.varqulabs.dolarblue.splash.SplashEvent
import com.varqulabs.dolarblue.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val splashViewModel = hiltViewModel<SplashViewModel>()

            val splashState by splashViewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) { splashViewModel.eventHandler(SplashEvent.Init) }

            DolarBlueTheme(
                darkTheme = splashState.isDarkTheme,
                user = splashState.currentUser
            ) {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}