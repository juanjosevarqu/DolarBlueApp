package com.varqulabs.dolarblue.core.domain.enums

enum class CurrencyTab(val tabName: String, val columnName: String) {
    BOB(tabName = "BOB", columnName = "conversion_table.pesosBob",),
    ARG(tabName = "ARG", columnName = "conversion_table.pesosArg"),
    USD(tabName = "USD", columnName = "conversion_table.dollar")
}