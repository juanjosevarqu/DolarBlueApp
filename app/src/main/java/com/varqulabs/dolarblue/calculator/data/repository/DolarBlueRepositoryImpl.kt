package com.varqulabs.dolarblue.calculator.data.repository

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
import com.varqulabs.dolarblue.calculator.data.remote.dto.mapToModel
import com.varqulabs.dolarblue.calculator.domain.model.DollarBlue
import com.varqulabs.dolarblue.calculator.domain.repository.DollarBlueRepository

class DolarBlueRepositoryImpl(
    private val service: DolarBlueService,
) : DollarBlueRepository {

    override suspend fun getDollarBlue(): DollarBlue {
        return service.getDolarBlue().mapToModel()
    }
}