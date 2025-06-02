package com.vkbao.currencypickerpresentation

import com.vkbao.landingbusinessapi.currencies.GetCurrenciesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyPickerViewModel @Inject constructor(
    private val currenciesProvider: GetCurrenciesProvider,
    private val
) {
}