package com.varqulabs.dolarblue.auth.domain.model

sealed class AuthResult<T> {
    data class Success<T>(val userRequest: T) : AuthResult<T>()
    data class Failure<T>(val error: Throwable) : AuthResult<T>()
}