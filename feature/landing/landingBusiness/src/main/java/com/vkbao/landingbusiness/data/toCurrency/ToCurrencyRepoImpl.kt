package com.vkbao.landingbusiness.data.toCurrency

import com.vkbao.landingbusiness.data.store.ExchangeRateStore
import com.vkbao.landingbusiness.domain.ToCurrencyRepo

class ToCurrencyRepoImpl(
    private val store: ExchangeRateStore
): ToCurrencyRepo {
    override suspend fun getToCurrency(): String {
        return store.fromCurrency
    }

    override suspend fun setToCurrency(currency: String) {
        store.toCurrency = currency
    }
}