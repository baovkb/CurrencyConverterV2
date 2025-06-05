package com.vkbao.currencypickerbusiness.domain

import com.vkbao.currencypickerbusiness.data.getCurrencies.repo.GetCurrenciesStatus

interface Repository<in I, out O> {
    suspend operator fun invoke(i: I): O
}

interface GetCurrenciesRepo : Repository<Unit, GetCurrenciesStatus>

interface SelectedCurrencyRepo {
    fun getSelectedCurrency(): String
    fun setSelectedCurrency(currency: String)

    fun getSelectionType(): String
    fun setSelectionType(type: String)
}