package com.vkbao.landingbusinessapi.currencies

import kotlinx.coroutines.flow.Flow

interface GetCurrenciesProvider {
    suspend fun getCurrencies(): Flow<Map<String, CurrencyData>>
}