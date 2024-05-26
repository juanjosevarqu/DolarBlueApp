package com.varqulabs.dolarblue.calculator.domain.repository

import com.varqulabs.dolarblue.calculator.domain.model.DolarBlue

interface DolarBlueRepository {

    suspend fun getDolarBlue(): DolarBlue
}