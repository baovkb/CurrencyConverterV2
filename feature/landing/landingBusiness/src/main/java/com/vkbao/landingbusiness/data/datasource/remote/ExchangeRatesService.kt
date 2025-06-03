package com.vkbao.landingbusiness.data.datasource.remote

import com.vkbao.landingbusiness.data.getExchangeRate.dto.SuccessResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//tech debt
interface ExchangeRatesService {

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currencies: String?
    ) : Response<SuccessResponse<Double>>
}