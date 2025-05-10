package com.vkbao.landingbusiness.domain

import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import com.vkbao.landingbusiness.domain.entity.Currency

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetCurrenciesRepo : Repository<Unit, Map<String, Currency>>

interface GetExchangeRatesRepo : Repository<ExchangeRate, Map<String, Double>>