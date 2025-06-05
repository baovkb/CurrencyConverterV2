package com.vkbao.currencypickerbusinessapi.selectedCurrency

interface SelectedCurrencyProvider {
    fun getSelectedCurrency(): String
    fun setSelectedCurrency(currency: String)

    fun getSelectionType(): String
    fun setSelectionType(type: String)

    fun clearSelectedCurrency()
}