package com.varqulabs.dolarblue.calculator.data.repository

import com.varqulabs.dolarblue.calculator.data.remote.DollarBlueService
import com.varqulabs.dolarblue.calculator.data.remote.dto.mapToModel
import com.varqulabs.dolarblue.calculator.domain.model.DollarBlue
import com.varqulabs.dolarblue.calculator.domain.repository.DollarBlueRepository

class DollarBlueRepositoryImpl(
    private val service: DollarBlueService,
) : DollarBlueRepository {

    override suspend fun getDollarBlue(): DollarBlue {
        return service.getDollarBlue().mapToModel()
    }
}