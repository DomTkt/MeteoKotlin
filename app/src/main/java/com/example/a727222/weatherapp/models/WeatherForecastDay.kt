package com.example.a727222.weatherapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherForecastDay(
        val dt: Int,
        val temp: Temp,
        val pressure: Double,
        val humidity: Int,
        val weather: List<Weather>,
        val speed: Double,
        val deg: Int,
        @SerializedName("clouds")
        val cloudss : Int,
        val rain: Double,
        val snow: Double,
        var city: City?,
        var day : String

) : Serializable