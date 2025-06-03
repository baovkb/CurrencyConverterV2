package com.vkbao.landingbusiness.domain

import com.vkbao.landingbusiness.data.getExchangeRate.request.ExchangeRate

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetExchangeRatesRepo : Repository<ExchangeRate, Map<String, Double>>

interface FromCurrencyRepo {
    suspend fun getFromCurrency(): String
    suspend fun setFromCurrency(currency: String)
}

interface ToCurrencyRepo {
    suspend fun getToCurrency(): String
    suspend fun setToCurrency(currency: String)
}