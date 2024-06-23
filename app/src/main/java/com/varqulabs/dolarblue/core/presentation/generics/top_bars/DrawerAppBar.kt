package com.varqulabs.dolarblue.core.presentation.generics.top_bars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onClickDrawer: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onClickDrawer) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu Icon Drawer"
                )
            }
        },
        modifier = modifier
    )
}