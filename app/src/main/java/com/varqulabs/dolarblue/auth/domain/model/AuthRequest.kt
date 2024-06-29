package com.varqulabs.dolarblue.auth.domain.model

data class AuthRequest(
    val email: String,
    val password: String
)