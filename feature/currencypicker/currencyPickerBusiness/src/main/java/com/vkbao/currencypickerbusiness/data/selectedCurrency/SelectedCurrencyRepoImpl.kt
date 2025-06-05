package com.vkbao.currencypickerbusiness.data.selectedCurrency

import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyRepo

class SelectedCurrencyRepoImpl(
    private val store: SelectedCurrenciesStore
): SelectedCurrencyRepo {
    override fun getSelectedCurrency(): String {
        return store.currency
    }

    override fun setSelectedCurrency(currency: String) {
        store.currency = currency
    }

    override fun getSelectionType(): String {
        return store.selectionType
    }

    override fun setSelectionType(type: String) {
        store.selectionType = type
    }
}