package com.example.a727222.weatherapp.models

import java.io.Serializable

data class Sys(
    val pod: String?,
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int
) : Serializable

