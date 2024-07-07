package com.varqulabs.dolarblue.history.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.generics.top_bars.DrawerAppBar
import com.varqulabs.dolarblue.history.presentation.HistoryEvent.OnClickDrawer

@Composable
fun HistoryScreen(
    state: HistoryState,
    eventHandler: (HistoryEvent) -> Unit
) {
    Scaffold(
        topBar = {
            DrawerAppBar(
                title = stringResource(id = R.string.copy_history),
                actionIcon = R.drawable.favoritos_1_p,
                onClickDrawer = { eventHandler(OnClickDrawer) }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

        }
    }
}