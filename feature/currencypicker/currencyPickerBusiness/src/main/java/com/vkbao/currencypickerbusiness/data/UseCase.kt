package com.vkbao.currencypickerbusiness.data

import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

interface UseCase <in I, out O> {
    suspend operator fun invoke (i: I) : O
}

interface StateFlowUseCase<in I, out O> : UseCase<I, Flow<State<O>>>

interface FromCurrencyUseCase {
    suspend fun getFromCurrency(): Flow<String>
    suspend fun setFromCurrency(currency: String): Flow<Unit>
}

interface ToCurrencyUseCase {
    suspend fun getToCurrency(): Flow<String>
    suspend fun setToCurrency(currency: String): Flow<Unit>
}