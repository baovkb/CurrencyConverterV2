package com.example.currencyconverterv2.feature.convert.convertBusiness.domain

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request.ExchangeRate
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.Currency

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetCurrenciesRepo : Repository<Unit, SuccessResponse<Currency>>

interface GetExchangeRatesRepo : Repository<ExchangeRate, SuccessResponse<Double>>