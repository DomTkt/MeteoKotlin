package com.example.a727222.weatherapp.interfaces

/// Think component in file architecture
/// This file should be in network package
interface IApiResponse<T> {
    fun onSuccess(obj: T?)
    fun onError(t: Throwable)
}