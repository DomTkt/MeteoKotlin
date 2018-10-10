package com.example.a727222.weatherapp.models


data class WeatherForecast(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<WeatherForecastDay>
)

