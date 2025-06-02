package com.vkbao.landingbusiness.data.getCurrencies.repo

import com.vkbao.landingbusiness.domain.entity.Currency

sealed class GetCurrenciesStatus {
    data class Success(val data: Map<String, Currency>) : GetCurrenciesStatus()
    //    data class InvalidToken(val code: String, val message: String)
    data class UndefinedError(val code: String, val message: String) : GetCurrenciesStatus()
}
