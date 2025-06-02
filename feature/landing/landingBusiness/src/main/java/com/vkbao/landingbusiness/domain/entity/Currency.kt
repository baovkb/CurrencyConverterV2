package com.vkbao.landingbusiness.domain.entity

import com.vkbao.landingbusinessapi.currencies.CurrencyData
import kotlinx.serialization.Serializable

@Serializable
data class Currency (
    val symbol: String,
    val name: String,
    val symbolNative: String,
    val decimalDigits: Int,
    val rounding: Int,
    val code: String,
    val namePlural: String,
    val type: String,
)