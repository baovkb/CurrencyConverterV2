package com.vkbao.currencypickerbusiness.data.fromCurrency

import com.vkbao.currencypickerbusiness.data.FromCurrencyRepo
import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore

class FromCurrencyRepoImpl(
    private val store: SelectedCurrenciesStore
): FromCurrencyRepo {
    override suspend fun getFromCurrency(): String = store.fromCurrency

    override suspend fun setFromCurrency(currency: String) {
        store.fromCurrency = currency
    }
}