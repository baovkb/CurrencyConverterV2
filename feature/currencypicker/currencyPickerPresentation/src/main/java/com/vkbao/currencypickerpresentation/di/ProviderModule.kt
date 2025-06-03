package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.domain.GetCurrenciesRepo
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase
import com.vkbao.currencypickerbusiness.domain.getCurrencies.GetCurrenciesProviderImpl
import com.vkbao.currencypickerbusiness.domain.selectedCurrency.SelectedCurrencyProviderImpl
import com.vkbao.currencypickerbusinessapi.currencies.GetCurrenciesProvider
import com.vkbao.currencypickerbusinessapi.selectedCurrency.SelectedCurrencyProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ProviderModule {

    @Provides
    @ViewModelScoped
    fun provideGetCurrenciesProvider(
        repo: GetCurrenciesRepo
    ): GetCurrenciesProvider = GetCurrenciesProviderImpl(repo)

    @Provides
    @ViewModelScoped
    fun provideSelectedCurrencyProvider(
        useCase: SelectedCurrencyUseCase
    ): SelectedCurrencyProvider = SelectedCurrencyProviderImpl(useCase)

}