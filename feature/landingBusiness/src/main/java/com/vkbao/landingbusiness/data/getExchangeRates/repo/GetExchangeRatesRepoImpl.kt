package com.vkbao.landingbusiness.data.getExchangeRates.repo

import com.vkbao.landingbusiness.data.datasource.remote.FreeCurrencyAPIService
import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo

class GetExchangeRatesRepoImpl(
    private val apiService: FreeCurrencyAPIService
) : GetExchangeRatesRepo {
    override suspend fun invoke(pairOfCurrency: ExchangeRate): Map<String, Double> {
        val response = apiService.getExchangeRates(
            pairOfCurrency.baseCurrency,
            pairOfCurrency.currencies
        )

        return if (response.isSuccessful) {
            response.body()!!
        } else {
            val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
            throw Exception(errorMsg)
        }
    }
}