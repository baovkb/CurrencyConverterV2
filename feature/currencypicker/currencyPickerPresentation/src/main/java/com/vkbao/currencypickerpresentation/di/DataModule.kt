package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.data.datasource.remote.CurrencyService
import com.vkbao.currencypickerbusiness.data.getCurrencies.repo.GetCurrenciesRepoImpl
import com.vkbao.currencypickerbusiness.data.selectedCurrency.SelectedCurrencyRepoImpl
import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore
import com.vkbao.currencypickerbusiness.domain.GetCurrenciesRepo
import com.vkbao.currencypickerbusiness.domain.SelectedCurrencyRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class DataModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGetCurrenciesRepo(
        apiService: CurrencyService,
        currencyStore: com.vkbao.currencypickerbusiness.data.store.CurrencyStore
    ) : GetCurrenciesRepo = GetCurrenciesRepoImpl(
        apiService,
        currencyStore
    )

    @Provides
    @ActivityRetainedScoped
    fun provideFromCurrencyRepo(
        store: SelectedCurrenciesStore
    ): SelectedCurrencyRepo = SelectedCurrencyRepoImpl(store)

}