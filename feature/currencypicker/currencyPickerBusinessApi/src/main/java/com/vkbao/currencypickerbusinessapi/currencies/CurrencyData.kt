package com.vkbao.currencypickerbusinessapi.currencies

data class CurrencyData(
    val symbol: String,
    val name: String,
    val symbolNative: String,
    val decimalDigits: Int,
    val rounding: Int,
    val code: String,
    val namePlural: String,
    val type: String,
)
