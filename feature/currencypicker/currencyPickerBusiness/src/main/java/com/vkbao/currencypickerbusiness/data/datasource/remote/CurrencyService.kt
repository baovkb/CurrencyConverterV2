package com.vkbao.currencypickerbusiness.data.datasource.remote

import com.vkbao.currencypickerbusiness.data.getCurrencies.dto.CurrencyResponse
import com.vkbao.currencypickerbusiness.data.getCurrencies.dto.SuccessResponse
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {
    @GET("currencies")
    suspend fun getCurrencies(): Response<SuccessResponse<CurrencyResponse>>
}