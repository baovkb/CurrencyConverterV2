package com.vkbao.currencypickerbusinessapi.currencies

import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

interface GetCurrenciesProvider {
    suspend fun getCurrencies(): Flow<State<Map<String, CurrencyData>>>
}