package com.vkbao.currencypickerpresentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vkbao.currencypickerbusiness.data.getCurrencies.repo.GetCurrenciesStatus
import com.vkbao.currencypickerbusiness.domain.GetCurrenciesUseCase
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase
import com.vkbao.currencypickerbusiness.domain.entity.Currency
import com.vkbao.currencypickerpresentation.model.CurrencyModel
import com.vkbao.currencypickerpresentation.model.GetCurrenciesState
import com.vkbao.utilities.flow.mapBoth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyPickerViewModel @Inject constructor(
    private val getCurrenciesUseCase: GetCurrenciesUseCase,
    private val selectedCurrencyUseCase: SelectedCurrencyUseCase
): ViewModel() {

    private val _currenciesState: MutableStateFlow<GetCurrenciesState> =
        MutableStateFlow(GetCurrenciesState.Init)
    val currencyState = _currenciesState.asStateFlow()

    private val _selectedCurrency = MutableStateFlow("")
    val selectedCurrency = _selectedCurrency.asStateFlow()

    fun getCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            _currenciesState.value = GetCurrenciesState.Loading

            getCurrenciesUseCase.invoke(Unit).mapBoth(
                error = { GetCurrenciesState.Error(it) },
                success = {
                    when(it) {
                        is GetCurrenciesStatus.Success -> {
                            GetCurrenciesState.Success(it.data.map {pair ->
                                convertToCurrencyModel(pair.value)
                            })
                        }

                        is GetCurrenciesStatus.UndefinedError -> {
                            GetCurrenciesState.Error(it.errorEntity)
                        }
                    }
                }
            ).collect {
                _currenciesState.value = it
            }
        }
    }

    private fun convertToCurrencyModel(currency: Currency): CurrencyModel {
        return CurrencyModel(
            currency.symbol,
            currency.name,
            currency.symbolNative,
            currency.decimalDigits,
            currency.code
        )
    }

    fun setSelectedCurrency(currency: String) {
        selectedCurrencyUseCase.setSelectedCurrency(currency)
    }

    fun getSelectedCurrency() {
        _selectedCurrency.value = selectedCurrencyUseCase.getSelectedCurrency()
    }
}