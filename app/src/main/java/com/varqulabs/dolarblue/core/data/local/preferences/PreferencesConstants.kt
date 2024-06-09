package com.varqulabs.dolarblue.core.data.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesConstants {

    val IS_DARK_MODE_KEY = booleanPreferencesKey("IS_DARK_MODE_KEY")

    val FAVOURITE_CURRENCY_KEY = stringPreferencesKey("FAVOURITE_CURRENCY_KEY")

}