package com.vkbao.landing.di

import com.vkbao.landingbusiness.domain.GetCurrenciesRepo
import com.vkbao.landingbusiness.domain.GetCurrenciesUseCase
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import com.vkbao.landingbusiness.domain.GetExchangeRatesUseCase
import com.vkbao.landingbusiness.domain.getCurrencies.GetCurrenciesProviderImpl
import com.vkbao.landingbusiness.domain.getCurrencies.GetCurrenciesUseCaseImpl
import com.vkbao.landingbusiness.domain.getExchangeRates.GetExchangeRatesUseCaseImpl
import com.vkbao.landingbusinessapi.currencies.GetCurrenciesProvider
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
    fun provideGetCurrenciesUseCase(
        getCurrenciesRepo: GetCurrenciesRepo
    ) : GetCurrenciesUseCase = GetCurrenciesUseCaseImpl(getCurrenciesRepo)

    @ViewModelScoped
    @Provides
    fun provideGetExchangeRatesUseCase(
        getExchangeRatesRepo: GetExchangeRatesRepo
    ) : GetExchangeRatesUseCase = GetExchangeRatesUseCaseImpl(getExchangeRatesRepo)

    @ViewModelScoped
    @Provides
    fun provideGetCurrencyProvider(
        getCurrenciesRepo: GetCurrenciesRepo
    ) : GetCurrenciesProvider = GetCurrenciesProviderImpl(getCurrenciesRepo)
}