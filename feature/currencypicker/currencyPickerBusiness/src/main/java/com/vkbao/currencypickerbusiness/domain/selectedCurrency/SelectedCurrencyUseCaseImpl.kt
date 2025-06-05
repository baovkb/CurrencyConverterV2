package com.vkbao.currencypickerbusiness.domain.selectedCurrency

import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyRepo
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase

class SelectedCurrencyUseCaseImpl(
    private val repo: SelectedCurrencyRepo
): SelectedCurrencyUseCase {
    override fun getSelectedCurrency(): String = repo.getSelectedCurrency()

    override fun setSelectedCurrency(currency: String) {
        repo.setSelectedCurrency(currency)
    }

    override fun getSelectionType(): String {
        return repo.getSelectionType()
    }

    override fun setSelectionType(type: String) {
        repo.setSelectionType(type)
    }
}