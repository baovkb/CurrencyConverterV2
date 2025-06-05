package com.vkbao.landingbusiness.domain.fromCurrency

import com.vkbao.landingbusiness.domain.FromCurrencyRepo
import com.vkbao.landingbusiness.domain.FromCurrencyUseCase

class FromCurrencyUseCaseImpl(
    private val repo: FromCurrencyRepo
): FromCurrencyUseCase {
    override fun getFromCurrency(): String = repo.getFromCurrency()

    override fun setFromCurrency(currency: String) {
        repo.setFromCurrency(currency)
    }
}