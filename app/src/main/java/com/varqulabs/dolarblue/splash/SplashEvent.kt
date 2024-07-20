package com.varqulabs.dolarblue.splash

sealed interface SplashEvent {

    data object Init : SplashEvent

}