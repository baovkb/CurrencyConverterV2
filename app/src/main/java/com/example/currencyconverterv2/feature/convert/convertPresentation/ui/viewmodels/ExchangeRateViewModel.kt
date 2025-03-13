package com.example.currencyconverterv2.feature.convert.convertPresentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.ApiState
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.dto.request.ExchangeRate
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesUseCase
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.CurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeRateViewModel @Inject constructor(
    private val exchangeRateUseCase: GetExchangeRatesUseCase
) : ViewModel() {

    private val _exchangeRates: MutableStateFlow<CurrencyState<Double>> = MutableStateFlow(CurrencyState.Idle)
    val exchangeRates = _exchangeRates.asStateFlow()

    fun getExchangeRates(exchangeRate: ExchangeRate) {
        viewModelScope.launch(Dispatchers.IO) {
            _exchangeRates.value = CurrencyState.Loading

            exchangeRateUseCase.invoke(exchangeRate).collect {
                _exchangeRates.value = when(it) {
                    is ApiState.Error -> CurrencyState.Error(it.message)
                    is ApiState.Success -> CurrencyState.Success(it.data)
                }
            }
        }
    }
}