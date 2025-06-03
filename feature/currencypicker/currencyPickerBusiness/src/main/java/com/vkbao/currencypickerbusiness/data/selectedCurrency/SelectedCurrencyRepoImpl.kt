package com.vkbao.currencypickerbusiness.data.selectedCurrency

import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyRepo

class SelectedCurrencyRepoImpl(
    private val store: SelectedCurrenciesStore
): SelectedCurrencyRepo {
    override suspend fun getSelectedCurrency(): String {
        return store.currency
    }

    override suspend fun setSelectedCurrency(currency: String) {
        store.currency = currency
    }
}