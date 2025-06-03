package com.vkbao.landing.di

import com.vkbao.landingbusiness.data.datasource.remote.ExchangeRatesService
import com.vkbao.networkbusinessapi.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideExchangeRatesService(retrofit: RetrofitProvider): ExchangeRatesService {
        return retrofit.invoke().create(ExchangeRatesService::class.java)
    }
}