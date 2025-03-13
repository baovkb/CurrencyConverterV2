package com.example.currencyconverterv2.feature.convert.convertPresentation.di

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.FreeCurrencyAPIService
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.repo.GetCurrenciesRepoImpl
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getExchangeRates.repo.GetExchangeRatesRepoImpl
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesRepo
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesRepo
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
        apiService: FreeCurrencyAPIService
    ) : GetCurrenciesRepo = GetCurrenciesRepoImpl(apiService)

    @Provides
    @ActivityRetainedScoped
    fun provideGetExchangeRatesRepo(
        apiService: FreeCurrencyAPIService
    ) : GetExchangeRatesRepo = GetExchangeRatesRepoImpl(apiService)
}