package com.example.a727222.weatherapp.models

import java.io.Serializable


data class WeatherCurrent(
        var coord: Coord,
        var weather: List<Weather>,
        var base: String,
        var main: Main,
        var visibility: Int,
        var wind: Wind,
        var clouds: Clouds,
        var dt: Int,
        var sys: Sys,
        var id: Int,
        var name: String,
        var cod: Int,
        var rain: Rain?,
        var snow: Snow?
) : Serializable
