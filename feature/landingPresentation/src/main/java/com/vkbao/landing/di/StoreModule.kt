package com.vkbao.landing.di

import com.vkbao.landingbusiness.data.store.CurrencyStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StoreModule {

    @Provides
    @Singleton
    fun provideCurrencyStore(): CurrencyStore = CurrencyStore
}