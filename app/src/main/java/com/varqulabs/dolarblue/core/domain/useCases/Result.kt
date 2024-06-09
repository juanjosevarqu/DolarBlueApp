package com.varqulabs.dolarblue.core.domain.useCases

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data object Loading: Result<Nothing>()
    class Error(val exception: UseCaseException) : Result<Nothing>()
}