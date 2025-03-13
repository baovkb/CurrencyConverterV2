package com.example.currencyconverterv2.feature.convert.convertBusiness.domain.getCurrencies

import android.util.Log
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.ApiState
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesRepo
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesUseCase
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCurrenciesUseCaseImpl(
    private val getCurrenciesRepo: GetCurrenciesRepo
) : GetCurrenciesUseCase {
    override suspend fun invoke(i: Unit): Flow<ApiState<SuccessResponse<Currency>>> = flow {
        try {
            val data = getCurrenciesRepo(Unit)
            emit(ApiState.Success(data))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message ?: ""))
        }
    }
}