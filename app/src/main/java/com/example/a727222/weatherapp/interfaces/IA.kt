package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherForecast

interface IA {
    fun updateWeatherForecastList()
    fun updateWeatherForecastListSearch()
    fun truncListWeather(weatherForecast : WeatherForecast?, truncLandscape : Int, truncPortrait : Int)
}