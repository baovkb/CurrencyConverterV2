package com.vkbao.landing.model

import com.vkbao.landingbusiness.state.ErrorEntity

sealed class CurrencyState {
    object Init: CurrencyState()
    object Loading: CurrencyState()
    data class Success(val currencies: List<CurrencyModel>): CurrencyState()
    data class Error(val errorEntity: ErrorEntity): CurrencyState()
}