package com.vkbao.landingbusiness.data.datasource.remote

import com.vkbao.landingbusiness.data.getCurrencies.dto.CurrencyResponse
import com.vkbao.landingbusiness.data.getCurrencies.dto.SuccessResponse
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