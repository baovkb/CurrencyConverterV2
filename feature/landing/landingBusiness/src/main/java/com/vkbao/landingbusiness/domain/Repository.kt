package com.vkbao.landingbusiness.domain

import com.vkbao.landingbusiness.data.getExchangeRate.request.ExchangeRate

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetExchangeRatesRepo : Repository<ExchangeRate, Map<String, Double>>

interface FromCurrencyRepo {
    fun getFromCurrency(): String
    fun setFromCurrency(currency: String)
}

interface ToCurrencyRepo {
    fun getToCurrency(): String
    fun setToCurrency(currency: String)
}