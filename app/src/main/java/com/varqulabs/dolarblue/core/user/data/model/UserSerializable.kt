package com.varqulabs.dolarblue.core.user.data.model

import kotlinx.serialization.Serializable

//Modelo serializable para poder guardar el usuario en DataStore como String
@Serializable
data class UserSerializable(
    val token: String,
    val userName: String,
    val email: String
)