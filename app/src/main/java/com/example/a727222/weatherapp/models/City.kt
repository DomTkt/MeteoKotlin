package com.example.a727222.weatherapp.models

import java.io.Serializable

data class City(
        val id: Int,
        val name: String,
        val coord: Coord,
        val country: String
) : Serializable