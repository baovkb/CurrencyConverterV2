package com.example.currencyconverterv2.feature.convert.convertPresentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.ApiState
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesUseCase
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.Currency
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.entity.CurrencyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currenciesUseCase: GetCurrenciesUseCase
) : ViewModel() {
    private val _currencies: MutableStateFlow<CurrencyState<Currency>> = MutableStateFlow(CurrencyState.Idle)
    val currencies = _currencies.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            _currencies.value = CurrencyState.Loading

            currenciesUseCase(Unit).collect {
                _currencies.value = when (it) {
                    is ApiState.Error -> CurrencyState.Error(it.message)
                    is ApiState.Success -> CurrencyState.Success(it.data)
                }
            }
        }
    }
}