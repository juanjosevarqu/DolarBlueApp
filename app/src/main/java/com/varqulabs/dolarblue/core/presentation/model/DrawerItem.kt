package com.varqulabs.dolarblue.core.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.varqulabs.dolarblue.navigation.Routes

data class DrawerItem(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: Routes
)