package com.example.a727222.weatherapp.interfaces

interface IApiResponse<T> {
    fun onSuccess(obj: T?)
    fun onError(t: Throwable)
}