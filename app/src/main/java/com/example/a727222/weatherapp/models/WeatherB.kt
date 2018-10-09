package com.example.a727222.weatherapp.models


data class WeatherB(
        val cod: String,
        val message: Double,
        val cnt: Int,
        val list: List<WeatherItem>,
        val city: City
)

