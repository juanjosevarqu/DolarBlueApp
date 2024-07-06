package com.varqulabs.dolarblue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.varqulabs.dolarblue.navigation.AppNavGraph
import com.varqulabs.dolarblue.core.presentation.desingsystem.DolarBlueTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DolarBlueTheme {
                val navController = rememberNavController()
                AppNavGraph(
                    navController = navController,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}