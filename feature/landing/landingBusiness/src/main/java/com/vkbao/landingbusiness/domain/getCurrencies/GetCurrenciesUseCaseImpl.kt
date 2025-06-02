package com.vkbao.landingbusiness.domain.getCurrencies

import com.vkbao.landingbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.landingbusiness.domain.GetCurrenciesRepo
import com.vkbao.landingbusiness.domain.GetCurrenciesUseCase
import com.vkbao.utilities.flow.flowState
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

class GetCurrenciesUseCaseImpl(
    private val getCurrenciesRepo: GetCurrenciesRepo
) : GetCurrenciesUseCase {
    override suspend fun invoke(i: Unit): Flow<State<GetCurrenciesStatus>> = flowState {
        getCurrenciesRepo.invoke(Unit)
    }
}