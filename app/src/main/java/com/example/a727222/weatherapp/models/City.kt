package com.example.a727222.weatherapp.models

import java.io.Serializable

data class City(
        var id: Int,
        var name: String,
        var coord: Coord,
        var country: String
) : Serializable