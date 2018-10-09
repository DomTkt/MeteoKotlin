package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherCurrent

interface IApiResponse<T> {
    fun onSuccess(obj: WeatherCurrent?)
    fun onError(t: Throwable)
}

interface IApiResponseList<T> {
    fun onSuccess(list: List<T>)
    fun onError(t: Throwable)
}