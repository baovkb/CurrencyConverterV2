package com.vkbao.landingbusiness.domain

import com.vkbao.landingbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetCurrenciesRepo : Repository<Unit, GetCurrenciesStatus>

interface GetExchangeRatesRepo : Repository<ExchangeRate, Map<String, Double>>