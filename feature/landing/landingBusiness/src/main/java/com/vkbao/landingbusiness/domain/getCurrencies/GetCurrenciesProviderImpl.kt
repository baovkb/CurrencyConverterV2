package com.vkbao.landingbusiness.domain.getCurrencies

import com.vkbao.landingbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.landingbusiness.domain.GetCurrenciesUseCase
import com.vkbao.landingbusiness.domain.entity.Currency
import com.vkbao.landingbusinessapi.currencies.CurrencyData
import com.vkbao.landingbusinessapi.currencies.GetCurrenciesProvider
import com.vkbao.utilities.flow.mapBoth
import kotlinx.coroutines.flow.Flow

class GetCurrenciesProviderImpl(
    private val useCase: GetCurrenciesUseCase
): GetCurrenciesProvider {
    override suspend fun getCurrencies(): Flow<Map<String, CurrencyData>> {
        return useCase.invoke(Unit).mapBoth(
            error = { emptyMap() },
            success = {
                when(it) {
                    is GetCurrenciesStatus.Success ->
                        it.data.mapValues { pair ->
                            toCurrencyData(pair.value)
                        }
                    is GetCurrenciesStatus.UndefinedError -> emptyMap()
                }
            }
        )
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