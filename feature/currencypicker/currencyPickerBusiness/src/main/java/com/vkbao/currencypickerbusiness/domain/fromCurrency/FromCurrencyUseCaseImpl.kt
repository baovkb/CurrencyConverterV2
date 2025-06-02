package com.vkbao.currencypickerbusiness.domain.fromCurrency

import com.vkbao.currencypickerbusiness.data.FromCurrencyRepo
import com.vkbao.currencypickerbusiness.data.FromCurrencyUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FromCurrencyUseCaseImpl(
    private val repo: FromCurrencyRepo
): FromCurrencyUseCase {
    override suspend fun getFromCurrency(): Flow<String> {
        return flowOf(repo.getFromCurrency())
    }

    override suspend fun setFromCurrency(currency: String): Flow<Unit> {
        return flowOf(repo.setFromCurrency(currency))
    }
}