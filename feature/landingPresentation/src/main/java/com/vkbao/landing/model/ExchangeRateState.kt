package com.vkbao.landing.model

import com.vkbao.landingbusiness.state.ErrorEntity

sealed class ExchangeRateState {
    object Init: ExchangeRateState()
    object Loading: ExchangeRateState()
    data class Success(val currencies: Map<String, Double>): ExchangeRateState()
    data class Error(val errorEntity: ErrorEntity): ExchangeRateState()
}