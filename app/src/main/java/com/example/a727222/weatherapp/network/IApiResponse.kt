package com.example.a727222.weatherapp.network

interface IApiResponse<T> {
    fun onSuccess(obj: T?)
    fun onError(t: Throwable)
}