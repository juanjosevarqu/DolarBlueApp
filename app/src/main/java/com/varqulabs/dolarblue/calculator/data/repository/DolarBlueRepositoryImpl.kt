package com.varqulabs.dolarblue.calculator.data.repository

import com.varqulabs.dolarblue.calculator.data.remote.DolarBlueService
import com.varqulabs.dolarblue.calculator.data.remote.mapToEntity
import com.varqulabs.dolarblue.calculator.domain.model.DolarBlueEntity
import com.varqulabs.dolarblue.calculator.domain.repository.DolarBlueRepository

class DolarBlueRepositoryImpl(
    private val service: DolarBlueService,
) : DolarBlueRepository {

    override suspend fun getDolarBlue(): DolarBlueEntity {
        return service.getDolarBlue().mapToEntity()
    }

}