package com.varqulabs.dolarblue.core.domain.extensions

fun Boolean?.orFalse(): Boolean = this ?: false

inline fun Boolean?.ifTrue(action: () -> Unit) {
    if (this == true) action()
}

inline fun Boolean?.ifFalse(action: () -> Unit) {
    if (this == false) action()
}