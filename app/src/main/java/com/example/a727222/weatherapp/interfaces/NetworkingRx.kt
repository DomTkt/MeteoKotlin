package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.Observable

interface NetworkingRx {
    fun getObservableWeatherCurrentSearch(city : String?) : Observable<WeatherCurrent>
    fun getObservableWeatherForecastSearch(city : String?) : Observable<WeatherForecast>
}