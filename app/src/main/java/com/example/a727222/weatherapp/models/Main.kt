package com.example.a727222.weatherapp.models

import java.io.Serializable

data class Main(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Double,
    val sea_level: Double,
    val grnd_level: Double,
    val humidity: Int,
    val temp_kf: Int
) : Serializable