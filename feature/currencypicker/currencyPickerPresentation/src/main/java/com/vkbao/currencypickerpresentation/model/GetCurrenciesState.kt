package com.vkbao.currencypickerpresentation.model

import com.vkbao.utilities.error.ErrorEntity

sealed class GetCurrenciesState {
    object Init: GetCurrenciesState()
    object Loading: GetCurrenciesState()
    data class Success(val data: List<CurrencyModel>): GetCurrenciesState()
    data class Error(val errorEntity: ErrorEntity): GetCurrenciesState()
}