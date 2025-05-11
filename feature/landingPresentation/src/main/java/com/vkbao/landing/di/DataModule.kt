package com.vkbao.landing.di

import com.vkbao.landingbusiness.data.datasource.remote.FreeCurrencyAPIService
import com.vkbao.landingbusiness.data.getCurrencies.repo.GetCurrenciesRepoImpl
import com.vkbao.landingbusiness.data.getExchangeRates.repo.GetExchangeRatesRepoImpl
import com.vkbao.landingbusiness.data.store.CurrencyStore
import com.vkbao.landingbusiness.domain.GetCurrenciesRepo
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataModule {
    @Provides
    @ActivityRetainedScoped
    fun provideGetCurrenciesRepo(
        apiService: FreeCurrencyAPIService,
        currencyStore: CurrencyStore
    ) : GetCurrenciesRepo = GetCurrenciesRepoImpl(apiService, currencyStore)

    @Provides
    @ActivityRetainedScoped
    fun provideGetExchangeRatesRepo(
        apiService: FreeCurrencyAPIService
    ) : GetExchangeRatesRepo = GetExchangeRatesRepoImpl(apiService)
}