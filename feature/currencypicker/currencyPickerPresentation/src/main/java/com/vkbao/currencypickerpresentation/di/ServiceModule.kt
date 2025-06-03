package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.data.datasource.remote.CurrencyService
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
    fun provideFreeCurrencyApiService(retrofit: RetrofitProvider): CurrencyService {
        return retrofit.invoke().create(CurrencyService::class.java)
    }
}