package com.example.a727222.weatherapp.models

data class WeatherItem(
        val dt: Int,
        val main: Main,
        val weather: List<Weather>,
        val clouds: Clouds,
        val wind: Wind,
        val sys: Sys,
        val dt_txt: String,
        val rain: Rain,
        val snow: Snow
)