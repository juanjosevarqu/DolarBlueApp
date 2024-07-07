package com.varqulabs.dolarblue.settings.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.generics.top_bars.SimpleAppBar
import com.varqulabs.dolarblue.navigation.Routes

fun NavGraphBuilder.settingsRoute(
    navigateBack: () -> Unit
) {
    composable<Routes.Settings> {

        Scaffold(
            topBar = {
                SimpleAppBar(title = stringResource(id = R.string.copy_settings)) {
                    navigateBack()
                }
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // TODO @JuanJo
            }
        }
    }
}