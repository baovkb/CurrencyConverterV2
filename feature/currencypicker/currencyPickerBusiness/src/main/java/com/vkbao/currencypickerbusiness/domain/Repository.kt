package com.vkbao.currencypickerbusiness.domain

import com.vkbao.currencypickerbusiness.data.getCurrencies.repo.GetCurrenciesStatus

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetCurrenciesRepo : Repository<Unit, GetCurrenciesStatus>

interface SelectedCurrencyRepo {
    suspend fun getSelectedCurrency(): String
    suspend fun setSelectedCurrency(currency: String)
}