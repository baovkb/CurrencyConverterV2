package com.vkbao.utilities.flow

import com.vkbao.utilities.error.ErrorEntity
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

suspend fun <T, R> Flow<State<T>>.mapBoth(
    success: suspend (value: T) -> R,
    error: (e: ErrorEntity) -> R
): Flow<R> {
    return this
        .map {
            when(it) {
                is State.Success -> success(it.data)
                is State.Error -> error(ErrorEntity(it.errorEntity.code, it.errorEntity.message))
            }
        }
        .catch {
            error(ErrorEntity("000", it.message ?: "Unexpected Error"))
        }
}

suspend fun <T> flowStateOf(
    value: T
): Flow<State<T>> = flow {
    emit(State.Success(value))
}

suspend fun flowStateErrorOf(
    errorEntity: ErrorEntity
): Flow<State.Error> = flow {
    emit(State.Error(errorEntity))
}

suspend fun <T> flowState(
    block: suspend () -> T
): Flow<State<T>> = flow {
    runCatching { block.invoke() }
        .onSuccess {
            emit(State.Success(it))
        }
        .onFailure {
            val errorEntity = ErrorEntity("000", it.message ?: "Unexpected Error")
            emit(State.Error(errorEntity))
        }
}
