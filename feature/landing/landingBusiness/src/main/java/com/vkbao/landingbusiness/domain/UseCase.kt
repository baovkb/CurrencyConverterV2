package com.vkbao.landingbusiness.domain

import com.vkbao.landingbusiness.data.getExchangeRate.request.ExchangeRate
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

interface UseCase <in I, out O> {
    suspend operator fun invoke (i: I) : O
}

interface StateFlowUseCase<in I, out O> : UseCase<I, Flow<State<O>>>

interface GetExchangeRatesUseCase : StateFlowUseCase<ExchangeRate, Map<String, Double>>

interface FromCurrencyUseCase {
    suspend fun getFromCurrency(): Flow<State<String>>
    suspend fun setFromCurrency(currency: String)
}

interface ToCurrencyUseCase {
    suspend fun getToCurrency(): Flow<State<String>>
    suspend fun setToCurrency(currency: String)
}