package com.vkbao.landing.di

import com.vkbao.landingbusiness.data.datasource.remote.ExchangeRatesService
import com.vkbao.landingbusiness.data.fromCurrency.FromCurrencyRepoImpl
import com.vkbao.landingbusiness.data.getExchangeRate.repo.GetExchangeRateRepoImpl
import com.vkbao.landingbusiness.data.store.ExchangeRateStore
import com.vkbao.landingbusiness.data.toCurrency.ToCurrencyRepoImpl
import com.vkbao.landingbusiness.domain.FromCurrencyRepo
import com.vkbao.landingbusiness.domain.GetExchangeRatesRepo
import com.vkbao.landingbusiness.domain.ToCurrencyRepo
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
    fun provideGetExchangeRatesRepo(
        apiService: ExchangeRatesService
    ) : GetExchangeRatesRepo = GetExchangeRateRepoImpl(apiService)

    @Provides
    @ActivityRetainedScoped
    fun provideFromCurrencyRepo(
        store: ExchangeRateStore
    ): FromCurrencyRepo = FromCurrencyRepoImpl(store)

    @Provides
    @ActivityRetainedScoped
    fun provideToCurrencyRepo(
        store: ExchangeRateStore
    ): ToCurrencyRepo = ToCurrencyRepoImpl(store)
}