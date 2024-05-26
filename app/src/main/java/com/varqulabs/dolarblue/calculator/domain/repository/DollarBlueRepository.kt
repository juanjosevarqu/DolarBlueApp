package com.varqulabs.dolarblue.calculator.domain.repository

import com.varqulabs.dolarblue.calculator.domain.model.DollarBlue

interface DollarBlueRepository {

    suspend fun getDollarBlue(): DollarBlue
}