package com.vkbao.currencypickerbusiness.data.getCurrencies.dto

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<out T>(
    val data: Map<String, T>
)