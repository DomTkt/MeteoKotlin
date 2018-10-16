package com.example.a727222.weatherapp.models

import java.io.Serializable

data class ForecastItem(
        val day: String,
        val temperature_max: Int?,
        val temperature_min: Int?,
        val icon : String?
) : Serializable
