package com.varqulabs.dolarblue.history.domain.model

data class ConversionsHistory(
   val currentExchangeRate: CurrentExchangeRate,
   val conversions: List<Conversion>
)
