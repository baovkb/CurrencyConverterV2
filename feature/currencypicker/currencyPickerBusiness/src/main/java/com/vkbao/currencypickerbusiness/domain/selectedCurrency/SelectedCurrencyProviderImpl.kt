package com.vkbao.currencypickerbusiness.domain.selectedCurrency

import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase
import com.vkbao.currencypickerbusinessapi.selectedCurrency.SelectedCurrencyProvider

class SelectedCurrencyProviderImpl(
    private val useCase: SelectedCurrencyUseCase
): SelectedCurrencyProvider {
    override fun getSelectedCurrency(): String = useCase.getSelectedCurrency()
    override fun setSelectedCurrency(
        currency: String
    ) = useCase.setSelectedCurrency(currency)

    override fun getSelectionType(): String = useCase.getSelectionType()

    override fun setSelectionType(type: String) = useCase.setSelectionType(type)

    override fun clearSelectedCurrency() {
        useCase.setSelectedCurrency("")
        useCase.setSelectionType("")
    }

}