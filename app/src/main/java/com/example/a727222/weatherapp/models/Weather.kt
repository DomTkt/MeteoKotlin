package com.example.a727222.weatherapp.models

import java.io.Serializable

data class Weather(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
) : Serializable