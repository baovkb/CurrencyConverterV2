package com.vkbao.networkpresentation.di

import com.vkbao.networkbusiness.domain.api.RetrofitProviderImpl
import com.vkbao.networkbusinessapi.RetrofitProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit() : RetrofitProvider = RetrofitProviderImpl()
}