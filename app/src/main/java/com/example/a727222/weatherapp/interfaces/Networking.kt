package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.network.IApiResponse
//Create to mode entry : Mock or Prod 
interface Networking {
    fun getCurrentWeatherSearch(callback : IApiResponse<WeatherCurrent>, searchCity : String?)
    fun getCurrentWeather(callback : IApiResponse<WeatherCurrent>)
    fun getWeatherForecastForOneWeek(callback : IApiResponse<WeatherForecast>)
    fun getWeatherForecastForOneWeekSearch(callback : IApiResponse<WeatherForecast>, searchCity : String?)
}
