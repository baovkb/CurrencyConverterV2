package com.vkbao.landingbusiness.data.fromCurrency

import com.vkbao.landingbusiness.data.store.ExchangeRateStore
import com.vkbao.landingbusiness.domain.FromCurrencyRepo

class FromCurrencyRepoImpl(
    private val store: ExchangeRateStore
): FromCurrencyRepo {
    override fun getFromCurrency(): String {
        return store.fromCurrency
    }

    override fun setFromCurrency(currency: String) {
        store.fromCurrency = currency
    }
}