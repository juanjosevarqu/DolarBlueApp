package com.varqulabs.dolarblue.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object HomeCalculatorScreen : Routes()

    @Serializable
    data class HistoryConversionsScreen(val idUser: String) : Routes()

}

@Serializable
data object Home

@Serializable
data class Profile(val id: String)