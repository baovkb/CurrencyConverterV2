package com.vkbao.currencypickerbusiness.domain.getCurrencies

import com.vkbao.currencypickerbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.currencypickerbusiness.domain.GetCurrenciesRepo
import com.vkbao.currencypickerbusiness.domain.entity.Currency
import com.vkbao.currencypickerbusinessapi.currencies.CurrencyData
import com.vkbao.currencypickerbusinessapi.currencies.GetCurrenciesProvider
import com.vkbao.utilities.flow.flowState
import com.vkbao.utilities.state.State
import kotlinx.coroutines.flow.Flow

class GetCurrenciesProviderImpl(
    private val repo: GetCurrenciesRepo
): GetCurrenciesProvider {
    override suspend fun getCurrencies(): Flow<State<Map<String, CurrencyData>>> = flowState {
       when(val status = repo.invoke(Unit)) {
           is GetCurrenciesStatus.Success ->
               status.data.mapValues { toCurrencyData(it.value) }

           is GetCurrenciesStatus.UndefinedError ->
               emptyMap()

           else -> emptyMap()
       }
    }

    private fun toCurrencyData(currency: Currency) = CurrencyData(
        currency.symbol,
        currency.name,
        currency.symbolNative,
        currency.decimalDigits,
        currency.rounding,
        currency.code,
        currency.namePlural,
        currency.type
    )
}