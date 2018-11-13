package com.example.a727222.weatherapp.models

import java.io.Serializable

data class Temp(
    var day: Double,
    var min: Double,
    var max: Double,
    var night: Double,
    var eve: Double,
    var morn: Double
) : Serializable