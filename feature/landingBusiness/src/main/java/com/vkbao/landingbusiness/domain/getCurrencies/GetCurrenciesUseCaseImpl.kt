package com.vkbao.landingbusiness.domain.getCurrencies

import com.vkbao.landingbusiness.domain.GetCurrenciesRepo
import com.vkbao.landingbusiness.domain.GetCurrenciesUseCase
import com.vkbao.landingbusiness.domain.entity.Currency
import com.vkbao.landingbusiness.state.ErrorEntity
import com.vkbao.landingbusiness.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrenciesUseCaseImpl(
    private val getCurrenciesRepo: GetCurrenciesRepo
) : GetCurrenciesUseCase {
    override suspend fun invoke(i: Unit): Flow<State<Map<String, Currency>>> = flow {
        try {
            val result = getCurrenciesRepo.invoke(Unit)
            emit(State.Success(result))
        } catch (e: Exception) {
            emit(State.Error(ErrorEntity("000", e.message ?: "")))
        }
    }

}