package com.vkbao.landingbusiness.data.toCurrency

import com.vkbao.landingbusiness.data.store.ExchangeRateStore
import com.vkbao.landingbusiness.domain.ToCurrencyRepo

class ToCurrencyRepoImpl(
    private val store: ExchangeRateStore
): ToCurrencyRepo {
    override fun getToCurrency(): String {
        return store.fromCurrency
    }

    override fun setToCurrency(currency: String) {
        store.toCurrency = currency
    }
}