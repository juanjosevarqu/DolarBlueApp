package com.varqulabs.dolarblue.calculator.data.remote

import retrofit2.http.GET

interface DolarBlueService {

    @GET("v2/latest")
    suspend fun getDolarBlue(): DolarBlueDto

}