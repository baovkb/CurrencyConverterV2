package com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<out T>(
    val data: Map<String, T>
)