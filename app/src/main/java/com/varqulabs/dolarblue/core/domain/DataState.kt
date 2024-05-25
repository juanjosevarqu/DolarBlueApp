package com.varqulabs.dolarblue.core.domain

sealed class DataState<out T> {

    data object Loading : DataState<Nothing>()

    data class Success<out T>(val data: T) : DataState<T>()

    data class Error(
        val code: Int? = null,
        val error: Any? = null,
        val message: String,
    ) : DataState<Nothing>()

    data object NetworkError : DataState<Nothing>()

    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error

    /**
     * Returns data from a [Success].
     * @throws ClassCastException If the current state is not [Success]
     *  */
    fun getSuccessData() = (this as Success).data
    fun getSuccessDataOrNull(): T? {
        return try {
            (this as Success).data
        } catch (e: Exception) {
            null
        }
    }

    /**
     * Returns an error message from an [Error]
     * @throws ClassCastException If the current state is not [Error]
     *  */
    fun getErrorMessage() = (this as Error).message
    fun getErrorMessageOrNull(): String? {
        return try {
            (this as Error).message
        } catch (e: Exception) {
            null
        }
    }
}