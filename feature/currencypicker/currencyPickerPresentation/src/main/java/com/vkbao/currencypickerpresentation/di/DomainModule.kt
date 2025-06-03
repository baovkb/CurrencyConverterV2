package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.domain.GetCurrenciesRepo
import com.vkbao.currencypickerbusiness.domain.GetCurrenciesUseCase
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyRepo
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyUseCase
import com.vkbao.currencypickerbusiness.domain.getCurrencies.GetCurrenciesUseCaseImpl
import com.vkbao.currencypickerbusiness.domain.selectedCurrency.SelectedCurrencyUseCaseImpl
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
    ): GetCurrenciesUseCase = GetCurrenciesUseCaseImpl(getCurrenciesRepo)

    @Provides
    @ViewModelScoped
    fun provideFromCurrencyUseCase(
        repo: SelectedCurrencyRepo
    ): SelectedCurrencyUseCase = SelectedCurrencyUseCaseImpl(repo)

}