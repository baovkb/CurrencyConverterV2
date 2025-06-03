package com.vkbao.landingbusiness.data.getExchangeRate.repo

import com.vkbao.landingbusiness.data.datasource.remote.ExchangeRatesService
import com.vkbao.landingbusiness.data.getExchangeRate.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import com.vkbao.utilities.error.Exception

class GetExchangeRateRepoImpl(
    private val apiService: ExchangeRatesService
) : GetExchangeRatesRepo {
    override suspend fun invoke(pairOfCurrency: ExchangeRate): Map<String, Double> {
        val response = apiService.getExchangeRates(
            pairOfCurrency.baseCurrency,
            pairOfCurrency.currencies
        )

        return if (response.isSuccessful) {
            response.body()!!.data
        } else {
            val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
            throw Exception(response.code().toString(), errorMsg)
        }
    }
}