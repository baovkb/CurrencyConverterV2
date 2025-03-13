package com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.repo

import android.util.Log
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.FreeCurrencyAPIService
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.toEntity
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesRepo
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCurrenciesRepoImpl(
    private val apiService: FreeCurrencyAPIService
) : GetCurrenciesRepo {
    override suspend fun invoke(i: Unit): SuccessResponse<Currency> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getCurrencies()

            if (response.isSuccessful) {
                val data = response.body()!!.data.mapValues { it.value.toEntity() }
                SuccessResponse(data)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
                throw Exception(errorMsg)
            }
        }
    }
}