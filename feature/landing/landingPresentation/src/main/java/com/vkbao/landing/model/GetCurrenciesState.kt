package com.vkbao.landing.model

import com.vkbao.landingbusiness.domain.entity.Currency
import com.vkbao.utilities.error.ErrorEntity

sealed class GetCurrenciesState {
    object Init: GetCurrenciesState()
    object Loading: GetCurrenciesState()
    data class Success(val data: Map<String, CurrencyModel>): GetCurrenciesState()
    data class Error(val errorEntity: ErrorEntity): GetCurrenciesState()
}