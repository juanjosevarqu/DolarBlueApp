package com.varqulabs.dolarblue.core.domain.useCases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase<T : Any, R : Any>(
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(input: T): Flow<Result<R>> =
        flow {
            emit(Result.Loading)
            emitAll(executeData(input).map { Result.Success(it) as Result<R> })
        }
            .flowOn(dispatcher)
            .catch {
                emit(Result.Error(UseCaseException.extractException(it)))
            }
    internal abstract fun executeData(input: T): Flow<R>
}
sealed class UseCaseException(
    override val cause: Throwable?
) : Throwable(cause) {
    class UnknownException(cause: Throwable) : UseCaseException(cause)
    companion object {
        fun extractException(throwable: Throwable): UseCaseException {
            return if (throwable is UseCaseException)
                throwable
            else UnknownException(throwable)
        }
    }
}