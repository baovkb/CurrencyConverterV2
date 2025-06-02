package com.vkbao.currencypickerpresentation.di

import com.vkbao.currencypickerbusiness.data.FromCurrencyRepo
import com.vkbao.currencypickerbusiness.data.FromCurrencyUseCase
import com.vkbao.currencypickerbusiness.data.ToCurrencyRepo
import com.vkbao.currencypickerbusiness.data.ToCurrencyUseCase
import com.vkbao.currencypickerbusiness.domain.fromCurrency.FromCurrencyUseCaseImpl
import com.vkbao.currencypickerbusiness.domain.toCurrency.ToCurrencyUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    @ViewModelScoped
    fun provideFromCurrencyUseCase(
        repo: FromCurrencyRepo
    ): FromCurrencyUseCase = FromCurrencyUseCaseImpl(repo)

    @Provides
    @ViewModelScoped
    fun provideToCurrencyUseCase(
        repo: ToCurrencyRepo
    ): ToCurrencyUseCase = ToCurrencyUseCaseImpl(repo)
}