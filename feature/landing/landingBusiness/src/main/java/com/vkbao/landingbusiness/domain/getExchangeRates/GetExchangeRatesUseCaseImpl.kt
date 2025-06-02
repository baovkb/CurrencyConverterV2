package com.vkbao.landingbusiness.domain.getExchangeRates

import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.utilities.error.ErrorEntity
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class GetExchangeRatesUseCaseImpl (
    private val exchangeRatesRepo: GetExchangeRatesRepo
) : GetExchangeRatesUseCase {
    override suspend fun invoke(i: ExchangeRate): Flow<com.vkbao.utilities.state.State<Map<String, Double>>> = flow {
        try {
            val result = exchangeRatesRepo.invoke(i)
            emit(com.vkbao.utilities.state.State.Success(result))
        } catch (e: Exception) {
            emit(com.vkbao.utilities.state.State.Error(
                com.vkbao.utilities.error.ErrorEntity(
                    "000",
                    e.message ?: ""
                )
            ))
        }
    }

}