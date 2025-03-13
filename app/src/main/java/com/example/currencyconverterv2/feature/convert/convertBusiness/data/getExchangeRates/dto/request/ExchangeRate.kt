package com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request

data class ExchangeRate(
    val baseCurrency: String,
    val currencies: String?
)
