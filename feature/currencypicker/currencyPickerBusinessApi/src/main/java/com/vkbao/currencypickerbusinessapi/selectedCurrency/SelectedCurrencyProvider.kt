package com.vkbao.currencypickerbusinessapi.selectedCurrency

import kotlinx.coroutines.flow.Flow

interface SelectedCurrencyProvider {
    suspend fun getSelectedCurrency(): Flow<String>
    suspend fun setSelectedCurrency(currency: String)
}