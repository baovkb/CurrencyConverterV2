package com.vkbao.landingbusiness.data.getCurrencies.dto

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<out T>(
    val data: Map<String, T>
)