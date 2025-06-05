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
    fun getFromCurrency(): String
    fun setFromCurrency(currency: String)
}

interface ToCurrencyUseCase {
    fun getToCurrency(): String
    fun setToCurrency(currency: String)
}