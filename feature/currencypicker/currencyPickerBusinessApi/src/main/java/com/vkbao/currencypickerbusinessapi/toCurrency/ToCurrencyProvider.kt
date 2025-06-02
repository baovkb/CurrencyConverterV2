package com.vkbao.currencypickerbusinessapi.toCurrency

interface ToCurrencyProvider {
    fun getToCurrency(): String
    fun setToCurrency(currency: String)
}