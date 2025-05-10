package com.vkbao.landing.di

import com.vkbao.landingbusiness.data.datasource.remote.FreeCurrencyAPIService
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
    fun provideFreeCurrencyApiService(retrofit: RetrofitProvider): FreeCurrencyAPIService {
        return retrofit.invoke().create(FreeCurrencyAPIService::class.java)
    }
}