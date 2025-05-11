package com.vkbao.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkbao.landing.model.CurrencyModel
import com.vkbao.landing.model.CurrencyState
import com.vkbao.landing.model.ExchangeRateState
import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetCurrenciesUseCase
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.landingbusiness.domain.entity.Currency
import com.vkbao.landingbusiness.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val currenciesUseCase: GetCurrenciesUseCase,
    private val exchangeRatesUseCase: GetExchangeRatesUseCase
) : ViewModel() {

    private val _currenciesState: MutableStateFlow<CurrencyState>
        = MutableStateFlow(CurrencyState.Init)
    val currenciesState = _currenciesState.asStateFlow()

    private val _exchangeRateState: MutableStateFlow<ExchangeRateState>
        = MutableStateFlow((ExchangeRateState.Init))
    val exchangeRateState = _exchangeRateState.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            _currenciesState.value = CurrencyState.Loading

            currenciesUseCase.invoke(Unit).collect {
                when(it) {
                    is State.Error -> {
                        _currenciesState.value = CurrencyState.Error(it.errorEntity)
                    }
                    is State.Success<Map<String, Currency>> -> {
                        val values = it.data.values.map { convertToCurrencyModel(it) }
                        _currenciesState.value = CurrencyState.Success(values)
                    }
                }
            }
        }
    }

    fun getExchangeRates(exchangeRate: ExchangeRate) {
        viewModelScope.launch(Dispatchers.IO) {
            _exchangeRateState.value = ExchangeRateState.Loading

            exchangeRatesUseCase.invoke(exchangeRate).collect {
                when(it) {
                    is State.Error -> {
                        _exchangeRateState.value = ExchangeRateState.Error(it.errorEntity)
                    }
                    is State.Success<Map<String, Double>> -> {
                        _exchangeRateState.value = ExchangeRateState.Success(it.data)
                    }
                }
            }
        }
    }

    private fun convertToCurrencyModel(currency: Currency): CurrencyModel {
        return CurrencyModel(
            symbol = currency.symbol,
            name = currency.name,
            symbolNative = currency.symbolNative,
            decimalDigits = currency.decimalDigits,
            code = currency.code
        )
    }
}
