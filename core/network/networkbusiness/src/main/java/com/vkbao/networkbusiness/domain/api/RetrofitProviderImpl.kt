package com.vkbao.networkbusiness.domain.api

import com.vkbao.networkbusiness.data.AuthInterceptor
import com.vkbao.networkbusinessapi.RetrofitProvider
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.vkbao.networkbusiness.BuildConfig

class RetrofitProviderImpl : RetrofitProvider {
    private val API_KEY = BuildConfig.API_KEY

    override fun invoke(): Retrofit {
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
}