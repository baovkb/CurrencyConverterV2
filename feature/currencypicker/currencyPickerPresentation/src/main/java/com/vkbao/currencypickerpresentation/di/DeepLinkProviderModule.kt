package com.vkbao.currencypickerpresentation.di

import com.vkbao.landingapi.currencyPicker.CurrencyPickerDeepLinkProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import navigation.deeplink.CurrencyPickerDeepLinkProviderImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
class DeepLinkProviderModule {

    @Provides
    @ActivityRetainedScoped
    fun provideCurrencyDeepLinkProvider(): CurrencyPickerDeepLinkProvider =
        CurrencyPickerDeepLinkProviderImpl()
}