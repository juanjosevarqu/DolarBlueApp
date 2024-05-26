package com.varqulabs.dolarblue.calculator.data.repository

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
import com.varqulabs.dolarblue.calculator.data.remote.dto.mapToModel
import com.varqulabs.dolarblue.calculator.domain.model.DolarBlue
import com.varqulabs.dolarblue.calculator.domain.repository.DolarBlueRepository

class DolarBlueRepositoryImpl(
    private val service: DolarBlueService,
) : DolarBlueRepository {

    override suspend fun getDolarBlue(): DolarBlue {
        return service.getDolarBlue().mapToModel()
    }
}