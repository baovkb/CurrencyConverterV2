package com.vkbao.landingbusiness.data.getCurrencies.repo

import com.vkbao.landingbusiness.data.datasource.remote.FreeCurrencyAPIService
import com.vkbao.landingbusiness.data.getCurrencies.dto.toEntity
import com.vkbao.landingbusiness.domain.GetCurrenciesRepo
import com.vkbao.landingbusiness.domain.entity.Currency

class GetCurrenciesRepoImpl(
    private val apiService: FreeCurrencyAPIService
) : GetCurrenciesRepo {
    override suspend fun invoke(i: Unit): Map<String, Currency> {
        val response = apiService.getCurrencies()

        return if (response.isSuccessful) {
            response.body()!!.mapValues { it.value.toEntity() }
        } else {
            val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
            throw Exception(errorMsg)
        }
    }
}