package com.vkbao.landingbusiness.data.getCurrencies.repo

import com.vkbao.landingbusiness.data.datasource.remote.FreeCurrencyAPIService
import com.vkbao.landingbusiness.data.getCurrencies.dto.toEntity
import com.vkbao.landingbusiness.data.store.CurrencyStore
import com.vkbao.landingbusiness.domain.GetCurrenciesRepo
import com.vkbao.landingbusiness.domain.entity.Currency

class GetCurrenciesRepoImpl(
    private val apiService: FreeCurrencyAPIService,
    private val currencyStore: CurrencyStore
) : GetCurrenciesRepo {
    override suspend fun invoke(i: Unit): Map<String, Currency> {
        return if (!currencyStore.entity.isNullOrEmpty()) {
            currencyStore.entity!!
        } else {
            val response = apiService.getCurrencies()

            if (response.isSuccessful) {
                val data = response.body()!!.data.mapValues { it.value.toEntity() }
                currencyStore.entity = data
                return data
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
                throw Exception(errorMsg)
            }
        }
    }
}