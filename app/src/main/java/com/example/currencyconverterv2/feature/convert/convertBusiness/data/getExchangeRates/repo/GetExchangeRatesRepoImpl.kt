package com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.repo

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.FreeCurrencyAPIService
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request.ExchangeRate
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetExchangeRatesRepoImpl(
    private val apiService: FreeCurrencyAPIService
) : GetExchangeRatesRepo {
    override suspend fun invoke(pairOfCurrency: ExchangeRate): SuccessResponse<Double> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getExchangeRates(
                pairOfCurrency.baseCurrency,
                pairOfCurrency.currencies
            )
            if (response.isSuccessful) {
                response.body()!!
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
                throw Exception(errorMsg)
            }
        }
    }
}