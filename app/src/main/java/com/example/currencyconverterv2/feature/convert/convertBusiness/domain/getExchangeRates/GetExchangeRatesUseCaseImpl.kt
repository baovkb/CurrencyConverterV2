package com.example.currencyconverterv2.feature.convert.convertBusiness.domain.getExchangeRates

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.ApiState
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request.ExchangeRate
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesRepo
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExchangeRatesUseCaseImpl (
    private val exchangeRatesRepo: GetExchangeRatesRepo
) : GetExchangeRatesUseCase {
    override suspend fun invoke(pairOfCurrency: ExchangeRate): Flow<ApiState<SuccessResponse<Double>>> = flow {
        try {
            val response = exchangeRatesRepo(pairOfCurrency)
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message ?: ""))
        }
    }
}