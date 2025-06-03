package com.vkbao.landingbusiness.domain.fromCurrency

import com.vkbao.landingbusiness.domain.FromCurrencyRepo
import com.vkbao.landingbusiness.domain.FromCurrencyUseCase
import com.vkbao.utilities.flow.flowState
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

class FromCurrencyUseCaseImpl(
    private val repo: FromCurrencyRepo
): FromCurrencyUseCase {
    override suspend fun getFromCurrency(): Flow<State<String>> = flowState {
        repo.getFromCurrency()
    }

    override suspend fun setFromCurrency(currency: String)  {
        repo.setFromCurrency(currency)
    }
}