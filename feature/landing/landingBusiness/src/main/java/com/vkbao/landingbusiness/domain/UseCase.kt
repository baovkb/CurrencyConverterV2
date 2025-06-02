package com.vkbao.landingbusiness.domain

import com.vkbao.landingbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

interface UseCase <in I, out O> {
    suspend operator fun invoke (i: I) : O
}

interface StateFlowUseCase<in I, out O> : UseCase<I, Flow<State<O>>>

interface GetCurrenciesUseCase : StateFlowUseCase<Unit, GetCurrenciesStatus>

interface GetExchangeRatesUseCase : StateFlowUseCase<ExchangeRate, Map<String, Double>>