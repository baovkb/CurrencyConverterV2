package com.vkbao.currencypickerbusiness.data

interface FromCurrencyRepo {
    suspend fun getFromCurrency(): String
    suspend fun setFromCurrency(currency: String)
}

interface ToCurrencyRepo {
    suspend fun getToCurrency(): String
    suspend fun setToCurrency(currency: String)
}