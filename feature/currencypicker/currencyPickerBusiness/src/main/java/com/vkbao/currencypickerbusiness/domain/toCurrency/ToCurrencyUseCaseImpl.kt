package com.vkbao.currencypickerbusiness.domain.toCurrency

import com.vkbao.currencypickerbusiness.data.ToCurrencyRepo
import com.vkbao.currencypickerbusiness.data.ToCurrencyUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ToCurrencyUseCaseImpl(
    private val repo: ToCurrencyRepo
): ToCurrencyUseCase {
    override suspend fun getToCurrency(): Flow<String> {
        return flowOf(repo.getToCurrency())
    }

    override suspend fun setToCurrency(currency: String): Flow<Unit> {
        return flowOf(repo.setToCurrency(currency))
    }
}