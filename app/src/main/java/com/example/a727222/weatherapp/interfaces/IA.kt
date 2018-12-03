package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherForecast

//Create this class to implement in presenter to Inject with Dagger
interface IA {
    fun updateWeatherForecastList()
    fun updateWeatherForecastListSearch()
    fun truncListWeather(weatherForecast : WeatherForecast?, truncLandscape : Int, truncPortrait : Int)
}