package com.varqulabs.dolarblue.calculator.domain.repository

import com.varqulabs.dolarblue.calculator.domain.model.DolarBlueEntity

interface DolarBlueRepository {

    suspend fun getDolarBlue(): DolarBlueEntity

}