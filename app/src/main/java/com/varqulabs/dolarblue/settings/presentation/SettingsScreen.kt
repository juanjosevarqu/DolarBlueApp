package com.varqulabs.dolarblue.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.generics.top_bars.SimpleAppBar
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnBack
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnChangeMyName
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnChangePasssword
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnLogout
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnSelectFavoriteCurrency
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnSignIn
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnToggleArgentinianNotifications
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateBolivianNewsEnabled
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.OnToggleDarkMode
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateDoNotDisturb
import com.varqulabs.dolarblue.settings.presentation.SettingsEvent.UpdateDollarNewsEnabled
import com.varqulabs.dolarblue.settings.presentation.components.FavoriteCurrencyConfigs
import com.varqulabs.dolarblue.settings.presentation.components.MyAccountActions
import com.varqulabs.dolarblue.settings.presentation.components.NewsSettings
import com.varqulabs.dolarblue.settings.presentation.components.ThemeConfigs

@Preview
@Composable
fun PreviewDemo(){
    SettingsScreen(
        state = SettingsState(),
        eventHandler = {}
    )
}

@Composable
fun SettingsScreen(
    state: SettingsState,
    eventHandler: (SettingsEvent) -> Unit
) {
    Scaffold(
        topBar = {
            SimpleAppBar(
                title = stringResource(id = R.string.copy_settings)
            ) { eventHandler(OnBack) }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    horizontal = 20.dp,
                    vertical = 40.dp
                )
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            ThemeConfigs(
                darkThemeEnabled = state.darkThemeEnabled,
                modifier = Modifier.fillMaxWidth(),
                onClickToggle = { eventHandler(OnToggleDarkMode) }
            )

            FavoriteCurrencyConfigs(
                favoriteCurrency = state.favoriteCurrency,
                modifier = Modifier.fillMaxWidth(),
                onSelectFavoriteCurrency = { eventHandler(OnSelectFavoriteCurrency(it)) }
            )

            NewsSettings(
                doNotDisturbEnabled = state.doNotDisturbEnabled,
                bolivianNewsEnabled = state.bolivianNewsEnabled,
                dollarNewsEnabled = state.dollarNewsEnabled,
                argentinianNewsEnabled = state.argentinianNewsEnabled,
                modifier = Modifier.fillMaxWidth(),
                onClickDoNotDisturbToggle = { eventHandler(UpdateDoNotDisturb(it)) },
                onClickBolivianNews = { eventHandler(UpdateBolivianNewsEnabled(it)) },
                onClickDollarNews = { eventHandler(UpdateDollarNewsEnabled(it)) },
                onClickArgentinianNews = { eventHandler(OnToggleArgentinianNotifications) }
            )

            MyAccountActions(
                isLoggedIn = state.currentUser != null, // TODO @JuanJo - Se puede usar Composition Local para obtener el usuario actual
                onClickChangeMyName = { eventHandler(OnChangeMyName) },
                onClickChangeMyPassword = { eventHandler(OnChangePasssword) },
                onClickExitMyAccount = { eventHandler(OnLogout) },
                onClickSignIn = { eventHandler(OnSignIn) }
            )
        }
    }
}