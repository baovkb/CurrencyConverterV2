package com.example.currencyconverterv2.feature.convert.convertBusiness.data.datasource.remote

sealed class ApiState<out T> {
    class Success<out T>(val data: T): ApiState<T>()
    class Error(val message: String): ApiState<Nothing>()
}