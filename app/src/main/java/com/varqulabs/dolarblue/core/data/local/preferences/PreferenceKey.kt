package com.varqulabs.dolarblue.core.data.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKey {

    val IS_DEFAULT_THEME_KEY = booleanPreferencesKey("IS_DEFAULT_THEME_KEY")

    val FAVOURITE_CURRENCY_KEY = stringPreferencesKey("FAVOURITE_CURRENCY_KEY")

}