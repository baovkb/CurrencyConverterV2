package com.vkbao.landingbusiness.domain.toCurrency

import com.vkbao.landingbusiness.domain.ToCurrencyRepo
import com.vkbao.landingbusiness.domain.ToCurrencyUseCase
import com.vkbao.utilities.flow.flowState
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

class ToCurrencyUseCaseImpl(
    private val repo: ToCurrencyRepo
): ToCurrencyUseCase {
    override suspend fun getToCurrency(): Flow<State<String>> = flowState {
        repo.getToCurrency()
    }

    override suspend fun setToCurrency(currency: String) {
        repo.setToCurrency(currency)
    }
}