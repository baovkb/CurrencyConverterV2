package com.vkbao.landingbusiness.domain.toCurrency

import com.vkbao.landingbusiness.domain.ToCurrencyRepo
import com.vkbao.landingbusiness.domain.ToCurrencyUseCase

class ToCurrencyUseCaseImpl(
    private val repo: ToCurrencyRepo
): ToCurrencyUseCase {
    override fun getToCurrency(): String = repo.getToCurrency()

    override fun setToCurrency(currency: String) {
        repo.setToCurrency(currency)
    }
}