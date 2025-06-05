package com.vkbao.currencypickerbusiness.data.getCurrencies.repo

import com.vkbao.currencypickerbusiness.domain.entity.Currency
import com.vkbao.utilities.error.ErrorEntity

sealed class GetCurrenciesStatus {
    data class Success(val data: Map<String, Currency>) : GetCurrenciesStatus()
    //    data class InvalidToken(val code: String, val message: String)
    data class UndefinedError(val errorEntity: ErrorEntity) : GetCurrenciesStatus()
}
