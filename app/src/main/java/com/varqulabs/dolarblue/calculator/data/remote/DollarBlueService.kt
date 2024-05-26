package com.varqulabs.dolarblue.calculator.data.remote

import com.varqulabs.dolarblue.calculator.data.remote.dto.DollarBlueDto
import retrofit2.http.GET

interface DollarBlueService {

    @GET("v2/latest")
    suspend fun getDollarBlue(): DollarBlueDto
}