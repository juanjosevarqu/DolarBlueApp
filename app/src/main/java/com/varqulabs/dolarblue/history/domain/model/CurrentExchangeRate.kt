package com.varqulabs.dolarblue.history.domain.model

import kotlinx.datetime.LocalDateTime

data class CurrentExchangeRate(
    val id: Int,
    val pesosBob: String,
    val pesosArg: String,
    val date: LocalDateTime
)
