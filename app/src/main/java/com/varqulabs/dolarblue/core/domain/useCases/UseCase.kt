package com.varqulabs.dolarblue.core.domain.useCases

import com.varqulabs.dolarblue.core.domain.DataState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase<T : Any, R : Any>(private val dispatcher: CoroutineDispatcher) {

    fun execute(input: T): Flow<DataState<R>> = flow {
        emit(DataState.Loading)
        emitAll(executeData(input).map { DataState.Success(it) as DataState<R> })
    }.flowOn(dispatcher).catch {
        emit(DataState.Error(
            code = it.hashCode(),
            error = it.cause,
            message = it.message?:""
        ))
    }

    protected abstract suspend fun executeData(input: T): Flow<R>

}