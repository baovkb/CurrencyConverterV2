package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.data.FromCurrencyRepo
import com.vkbao.currencypickerbusiness.data.ToCurrencyRepo
import com.vkbao.currencypickerbusiness.data.fromCurrency.FromCurrencyRepoImpl
import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore
import com.vkbao.currencypickerbusiness.data.toCurrency.ToCurrencyRepoImpl
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
    fun provideFromCurrencyRepo(
        store: SelectedCurrenciesStore
    ): FromCurrencyRepo = FromCurrencyRepoImpl(store)

    @Provides
    @ActivityRetainedScoped
    fun provideToCurrencyRepo(
        store: SelectedCurrenciesStore
    ): ToCurrencyRepo = ToCurrencyRepoImpl(store)
}