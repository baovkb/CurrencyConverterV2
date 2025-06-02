package com.vkbao.currencypickerbusinessapi.fromCurrency

interface FromCurrencyProvider {
    fun getFromCurrency(): String
    fun setFromCurrency(currency: String)
}