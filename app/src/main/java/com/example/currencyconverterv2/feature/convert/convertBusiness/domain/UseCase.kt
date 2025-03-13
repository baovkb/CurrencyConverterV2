package com.example.currencyconverterv2.feature.convert.convertBusiness.domain

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.ApiState
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request.ExchangeRate
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.Currency
import kotlinx.coroutines.flow.Flow


interface UseCase <in I, out O> {
    suspend operator fun invoke (i: I) : O
}

interface StateFlowUseCase<in I, out O> : UseCase<I, Flow<ApiState<O>>>

interface GetCurrenciesUseCase : StateFlowUseCase<Unit, SuccessResponse<Currency>>

interface GetExchangeRatesUseCase : StateFlowUseCase<ExchangeRate, SuccessResponse<Double>>