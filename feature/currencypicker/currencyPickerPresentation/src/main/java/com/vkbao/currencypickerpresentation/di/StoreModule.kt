package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.data.store.CurrencyStore
import com.vkbao.currencypickerbusiness.data.store.SelectedCurrenciesStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoreModule {

    @Provides
    @Singleton
    fun provideCurrencyStore() = CurrencyStore

    @Provides
    @Singleton
    fun provideSelectedCurrenciesStore() = SelectedCurrenciesStore
}