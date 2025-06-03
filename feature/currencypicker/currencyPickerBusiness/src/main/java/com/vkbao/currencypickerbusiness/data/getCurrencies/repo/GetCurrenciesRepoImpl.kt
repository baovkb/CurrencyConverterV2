package com.vkbao.currencypickerbusiness.data.getCurrencies.repo

import com.vkbao.currencypickerbusiness.data.datasource.remote.CurrencyService
import com.vkbao.currencypickerbusiness.data.getCurrencies.dto.toEntity
import com.vkbao.currencypickerbusiness.data.store.CurrencyStore
import com.vkbao.currencypickerbusiness.domain.GetCurrenciesRepo
import com.vkbao.utilities.error.Exception

class GetCurrenciesRepoImpl(
    private val apiService: CurrencyService,
    private val currencyStore: CurrencyStore
) : GetCurrenciesRepo {
    override suspend fun invoke(i: Unit): GetCurrenciesStatus {
        return if (!currencyStore.entity.isNullOrEmpty()) {
            val dataFromStore = currencyStore.entity!!
            GetCurrenciesStatus.Success(dataFromStore)
        } else {
            val response = apiService.getCurrencies()

            if (response.isSuccessful) {
                val data = response.body()!!.data.mapValues { it.value.toEntity() }
                currencyStore.entity = data
                GetCurrenciesStatus.Success(data)
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Unexpected Error"
                when(response.code()) {
                    in 400..499 -> GetCurrenciesStatus.UndefinedError(
                        response.code().toString(),
                        errorMsg
                    )
                    else -> throw Exception(response.code().toString(), errorMsg)
                }
            }
        }
    }
}