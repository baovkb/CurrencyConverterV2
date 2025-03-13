package com.example.currencyconverterv2.feature.convert.convertPresentation.di

import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesRepo
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetCurrenciesUseCase
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesRepo
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.GetExchangeRatesUseCase
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.getCurrencies.GetCurrenciesUseCaseImpl
import com.example.currencyconverterv2.feature.convert.convertBusiness.domain.getExchangeRates.GetExchangeRatesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

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
}