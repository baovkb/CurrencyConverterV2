package com.vkbao.landing.model

import com.vkbao.landingbusiness.domain.entity.Currency
import com.vkbao.landingbusiness.state.ErrorEntity

sealed class CurrencyState {
    object Init: CurrencyState()
    object Loading: CurrencyState()
    data class Success(val currencies: Map<String, Currency>): CurrencyState()
    data class Error(val errorEntity: ErrorEntity): CurrencyState()
}