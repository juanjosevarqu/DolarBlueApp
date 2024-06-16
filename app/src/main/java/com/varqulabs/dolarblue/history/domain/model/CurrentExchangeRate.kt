package com.varqulabs.dolarblue.history.domain.model

import java.time.LocalDateTime

data class CurrentExchangeRate(
    val id: Int,
    val pesosBob: String,
    val pesosArg: String,
    val date: LocalDateTime,
)
