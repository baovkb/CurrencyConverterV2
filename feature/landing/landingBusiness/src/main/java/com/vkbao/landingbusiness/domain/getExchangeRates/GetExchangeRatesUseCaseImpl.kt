package com.vkbao.landingbusiness.domain.getExchangeRates

import com.vkbao.landingbusiness.data.getExchangeRate.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.utilities.error.ErrorEntity
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetExchangeRatesUseCaseImpl (
    private val exchangeRatesRepo: GetExchangeRatesRepo
) : GetExchangeRatesUseCase {
    override suspend fun invoke(i: ExchangeRate): Flow<State<Map<String, Double>>> = flow {
        try {
            val result = exchangeRatesRepo.invoke(i)
            emit(State.Success(result))
        } catch (e: Exception) {
            emit(State.Error(ErrorEntity(
                    "000",
                    e.message ?: "")
            ))
        }
    }

}