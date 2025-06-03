package com.vkbao.currencypickerbusiness.domain.selectedCurrency

import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyRepo
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SelectedCurrencyUseCaseImpl(
    private val repo: SelectedCurrencyRepo
): SelectedCurrencyUseCase {
    override suspend fun getSelectedCurrency(): Flow<String> {
        return flowOf(repo.getSelectedCurrency())
    }

    override suspend fun setSelectedCurrency(currency: String) {
        repo.setSelectedCurrency(currency)
    }
}