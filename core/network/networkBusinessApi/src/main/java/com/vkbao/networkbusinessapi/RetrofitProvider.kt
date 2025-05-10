package com.vkbao.networkbusinessapi

import retrofit2.Retrofit

interface RetrofitProvider {
    fun invoke(): Retrofit
}