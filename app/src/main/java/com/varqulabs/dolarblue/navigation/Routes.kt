package com.varqulabs.dolarblue.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Calculator : Routes()

    @Serializable
    data object ConversionHistory : Routes()

}