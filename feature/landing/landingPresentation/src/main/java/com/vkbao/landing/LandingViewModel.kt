package com.vkbao.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkbao.currencypickerbusinessapi.currencies.CurrencyData
import com.vkbao.landing.model.CurrencyModel
import com.vkbao.landing.model.ExchangeRateState
import com.vkbao.landing.model.GetCurrenciesState
import com.vkbao.landingbusiness.data.getExchangeRate.request.ExchangeRate
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.currencypickerbusinessapi.currencies.GetCurrenciesProvider
import com.vkbao.currencypickerbusinessapi.selectedCurrency.SelectedCurrencyProvider
import com.vkbao.landing.model.SelectionCurrency
import com.vkbao.landingapi.currencyPicker.CurrencyPickerDeepLinkProvider
import com.vkbao.landingbusiness.domain.FromCurrencyUseCase
import com.vkbao.landingbusiness.domain.ToCurrencyUseCase
import com.vkbao.utilities.flow.mapBoth
import com.vkbao.utilities.state.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val currenciesProvider: GetCurrenciesProvider,
    private val exchangeRatesUseCase: GetExchangeRatesUseCase,
    private val selectedCurrencyProvider: SelectedCurrencyProvider,
    private val fromCurrencyUseCase: FromCurrencyUseCase,
    private val toCurrencyUseCase: ToCurrencyUseCase
) : ViewModel() {

    private val _currenciesState: MutableStateFlow<GetCurrenciesState>
        = MutableStateFlow(GetCurrenciesState.Init)
    val currenciesState = _currenciesState.asStateFlow()

    private val _exchangeRateState: MutableStateFlow<ExchangeRateState>
        = MutableStateFlow((ExchangeRateState.Init))
    val exchangeRateState = _exchangeRateState.asStateFlow()

    private val _selectionCurrency = MutableStateFlow<SelectionCurrency?>(null)
    val selectionCurrency = _selectionCurrency.asStateFlow()

    fun getSelectionCurrency() {
        val type = selectedCurrencyProvider.getSelectionType()
        val currency = selectedCurrencyProvider.getSelectedCurrency()
        _selectionCurrency.value = SelectionCurrency(type, currency)
    }

    fun setSelectionCurrency(type: String, currency: String) {
        selectedCurrencyProvider.setSelectionType(type)
        selectedCurrencyProvider.setSelectedCurrency(currency)
    }

    fun clearSelectionCurrency() {
        selectedCurrencyProvider.clearSelectedCurrency()
    }

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            _currenciesState.value = GetCurrenciesState.Loading

            currenciesProvider.getCurrencies()
                .mapBoth(
                    error = { GetCurrenciesState.Error(it) },
                    success = {
                        val currenciesList = it.map { pair ->
                            convertToCurrencyModel(pair.value)
                        }
                        GetCurrenciesState.Success(currenciesList)
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

    private fun convertToCurrencyModel(currencyData: CurrencyData): CurrencyModel {
        return CurrencyModel(
            symbol = currencyData.symbol,
            name = currencyData.name,
            symbolNative = currencyData.symbolNative,
            decimalDigits = currencyData.decimalDigits,
            code = currencyData.code
        )
    }
}
