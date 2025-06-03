package com.vkbao.landing.di

import com.vkbao.landingbusiness.domain.FromCurrencyRepo
import com.vkbao.landingbusiness.domain.FromCurrencyUseCase
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.landingbusiness.domain.ToCurrencyRepo
import com.vkbao.landingbusiness.domain.ToCurrencyUseCase
import com.vkbao.landingbusiness.domain.fromCurrency.FromCurrencyUseCaseImpl
import com.vkbao.landingbusiness.domain.getExchangeRates.GetExchangeRatesUseCaseImpl
import com.vkbao.landingbusiness.domain.toCurrency.ToCurrencyUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @ViewModelScoped
    @Provides
    fun provideGetExchangeRatesUseCase(
        getExchangeRatesRepo: GetExchangeRatesRepo
    ) : GetExchangeRatesUseCase = GetExchangeRatesUseCaseImpl(getExchangeRatesRepo)

    @Provides
    @ViewModelScoped
    fun provideFromCurrencyUseCase(
        repo: FromCurrencyRepo
    ): FromCurrencyUseCase = FromCurrencyUseCaseImpl(repo)

    @Provides
    @ViewModelScoped
    fun provideToCurrencyUseCase(
        repo: ToCurrencyRepo
    ): ToCurrencyUseCase = ToCurrencyUseCaseImpl(repo)
}