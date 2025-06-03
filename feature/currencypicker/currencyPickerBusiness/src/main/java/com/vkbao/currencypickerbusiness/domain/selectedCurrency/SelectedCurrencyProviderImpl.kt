package com.vkbao.currencypickerbusiness.domain.selectedCurrency

import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase
import com.vkbao.currencypickerbusinessapi.selectedCurrency.SelectedCurrencyProvider
import kotlinx.coroutines.flow.Flow

class SelectedCurrencyProviderImpl(
    private val useCase: SelectedCurrencyUseCase
): SelectedCurrencyProvider {
    override suspend fun getSelectedCurrency(): Flow<String> = useCase.getSelectedCurrency()
    override suspend fun setSelectedCurrency(
        currency: String
    ) = useCase.setSelectedCurrency(currency)

}