package com.vkbao.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkbao.landing.model.CurrencyModel
import com.vkbao.landing.model.ExchangeRateState
import com.vkbao.landing.model.GetCurrenciesState
import com.vkbao.landingbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.landingbusiness.data.getExchangeRates.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetCurrenciesUseCase
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.landingbusiness.domain.entity.Currency
import com.vkbao.utilities.error.ErrorEntity
import com.vkbao.utilities.flow.mapBoth
import com.vkbao.utilities.state.State
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

    private val _currenciesState: MutableStateFlow<GetCurrenciesState>
        = MutableStateFlow(GetCurrenciesState.Init)
    val currenciesState = _currenciesState.asStateFlow()

    private val _exchangeRateState: MutableStateFlow<ExchangeRateState>
        = MutableStateFlow((ExchangeRateState.Init))
    val exchangeRateState = _exchangeRateState.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            _currenciesState.value = GetCurrenciesState.Loading

            currenciesUseCase.invoke(Unit)
                .mapBoth(
                    error = { GetCurrenciesState.Error(it) },
                    success = {
                        when(it) {
                            is GetCurrenciesStatus.Success ->
                                GetCurrenciesState.Success(it.data.mapValues { pair ->
                                    convertToCurrencyModel(pair.value) }
                                )
                            is GetCurrenciesStatus.UndefinedError ->
                                GetCurrenciesState.Error(
                                    ErrorEntity(
                                        it.code,
                                        it.message
                                    )
                                )
                        }
                    }
                )
                .collect {
                    _currenciesState.value = it
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
