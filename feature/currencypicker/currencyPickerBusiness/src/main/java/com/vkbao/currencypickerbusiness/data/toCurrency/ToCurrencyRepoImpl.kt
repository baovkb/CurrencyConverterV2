package com.vkbao.currencypickerbusiness.data.toCurrency

import com.vkbao.currencypickerbusiness.data.ToCurrencyRepo
import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore

class ToCurrencyRepoImpl(
    private val store: SelectedCurrenciesStore
): ToCurrencyRepo {
    override suspend fun getToCurrency(): String = store.toCurrency

    override suspend fun setToCurrency(currency: String) {
       store.toCurrency = currency
    }
}