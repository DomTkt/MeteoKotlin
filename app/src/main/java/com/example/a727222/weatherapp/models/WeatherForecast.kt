package com.example.a727222.weatherapp.models

import java.io.Serializable


data class WeatherForecast(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<WeatherForecastDay>
) : Serializable

