package com.vkbao.landingbusiness.data.getCurrencies.dto

import com.vkbao.landingbusiness.domain.entity.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyResponse (
    val symbol: String?,
    val name: String?,
    @SerialName("symbol_native") val symbolNative: String?,
    @SerialName("decimal_digits") val decimalDigits: Int?,
    val rounding: Int?,
    val code: String?,
    @SerialName("name_plural") val namePlural: String?,
    val type: String?,
)

fun CurrencyResponse.toEntity() = Currency(
    symbol ?: "",
    name ?: "",
    symbolNative ?: "",
    decimalDigits ?: 0,
    rounding ?: 0,
    code ?: "",
    namePlural ?: "",
    type ?: ""
)