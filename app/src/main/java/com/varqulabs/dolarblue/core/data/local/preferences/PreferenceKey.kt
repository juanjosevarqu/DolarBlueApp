package com.varqulabs.dolarblue.core.data.local.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKey {

    val DEFAULT_THEME_ENABLED_KEY = booleanPreferencesKey("DEFAULT_THEME_ENABLED_KEY")

    val FAVOURITE_CURRENCY_KEY = stringPreferencesKey("FAVOURITE_CURRENCY_KEY")

    val NOTIFICATIONS_ENABLED_KEY = booleanPreferencesKey("NOTIFICATIONS_ENABLED_KEY")

    val BOLIVIAN_NEWS_ENABLED_KEY = booleanPreferencesKey("BOLIVIAN_NEWS_ENABLED_KEY")

    val DOLLAR_NEWS_ENABLED_KEY = booleanPreferencesKey("DOLLAR_NEWS_ENABLED_KEY")

    val ARGENTINIAN_NEWS_ENABLED_KEY = booleanPreferencesKey("ARGENTINIAN_NEWS_ENABLED_KEY")

    val USER_SESSION = stringPreferencesKey("USER_SESSION")

}