package com.example.a727222.weatherapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherForecastDay(
        var dt: Int,
        var temp: Temp,
        var pressure: Double,
        var humidity: Int,
        var weather: List<Weather>,
        var speed: Double,
        var deg: Int,
        @SerializedName("clouds")
        var cloudss : Int,
        var rain: Double,
        var snow: Double,
        var city: City?,
        var day : String?

) : Serializable