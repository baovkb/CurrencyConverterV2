package com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse

sealed class CurrencyState<out O> {
    object Idle : CurrencyState<Nothing>()

    object Loading : CurrencyState<Nothing>()

    class Error (
        val message: String
    ) : CurrencyState<Nothing>()

    class Success<out O> (
        val successResponse: SuccessResponse<O>
    ) : CurrencyState<O>()
}