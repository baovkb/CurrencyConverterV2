package com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote

import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.CurrencyResponse
import com.example.currencyconverterv2.feature.convert.convertBusiness.data.getCurrencies.dto.SuccessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FreeCurrencyAPIService {
    @GET("currencies")
    suspend fun getCurrencies(): Response<SuccessResponse<CurrencyResponse>>

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currencies: String?
    ) : Response<SuccessResponse<Double>>
}