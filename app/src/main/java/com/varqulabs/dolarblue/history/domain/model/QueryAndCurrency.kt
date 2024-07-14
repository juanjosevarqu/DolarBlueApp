package com.varqulabs.dolarblue.history.domain.model

data class QueryAndCurrency(
    val currencyColumnName: String,
    val searchQuery: String,
)