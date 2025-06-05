package com.vkbao.currencypickerbusiness.domain

import com.vkbao.currencypickerbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

interface UseCase <in I, out O> {
    suspend operator fun invoke (i: I) : O
}

interface StateFlowUseCase<in I, out O> : UseCase<I, Flow<State<O>>>

interface GetCurrenciesUseCase : StateFlowUseCase<Unit, GetCurrenciesStatus>

interface SelectedCurrencyUseCase {
    fun getSelectedCurrency(): String
    fun setSelectedCurrency(currency: String)

    fun getSelectionType(): String
    fun setSelectionType(type: String)
}
