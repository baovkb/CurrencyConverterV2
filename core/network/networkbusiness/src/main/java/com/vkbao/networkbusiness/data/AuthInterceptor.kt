package com.vkbao.networkbusiness.data

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("apikey", apiKey)
        val request = requestBuilder.build()

        println(request.toString())
        return chain.proceed(request)
    }
}