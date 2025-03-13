package com.example.currencyconverterv2.feature.convert.convertPresentation.di

import com.example.currencyconverterv2.BuildConfig
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote.FreeCurrencyAPIService
import com.example.currencyconverterv2.feature.convert.convertPresentation.utils.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {
    private const val API_KEY = BuildConfig.API_KEY

    @ActivityRetainedScoped
    @Provides
    fun provideRetrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
        val mediaType = "application/json; charset=UTF8".toMediaType()

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(API_KEY))
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.freecurrencyapi.com/v1/")
            .addConverterFactory(json.asConverterFactory(mediaType))
            .client(client)
            .build()
    }

    @ActivityRetainedScoped
    @Provides
    fun provideFreeCurrencyAPIService(
        retrofit: Retrofit
    ): FreeCurrencyAPIService = retrofit.create(FreeCurrencyAPIService::class.java)
}