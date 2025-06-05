package com.vkbao.currencypickerpresentation.model

data class CurrencyModel (
    val symbol: String,
    val name: String,
    val symbolNative: String,
    val decimalDigits: Int,
    val code: String,
)