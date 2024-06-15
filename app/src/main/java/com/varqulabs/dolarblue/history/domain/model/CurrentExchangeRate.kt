package com.varqulabs.dolarblue.history.domain.model

import java.time.LocalDateTime

data class CurrentExchangeRate(
    val id: Int? = null,
    val pesosBob: String,
    val pesosArg: String,
    val date: LocalDateTime,
)
