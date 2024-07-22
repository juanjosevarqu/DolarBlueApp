package com.varqulabs.dolarblue.core.domain.enums

import kotlinx.serialization.Serializable

enum class Currency(val code: String) {
    BOLIVIANO("BOB"),
    DOLLAR("USD"),
    PESOS("ARS")
}

fun Currency.toSerializable() = CurrencySerializable(
    name = this.name,
    code = this.code
)

@Serializable
data class CurrencySerializable(
    val name : String,
    val code : String
)

fun CurrencySerializable.toCurrency() = Currency.valueOf(name)