package com.example.a727222.weatherapp.models

import java.io.Serializable


data class WeatherForecast(
    var city: City,
    var cod: String,
    var message: Double?,
    var cnt: Int,
    var list: List<WeatherForecastDay>?
) : Serializable {
    constructor() : this(city = City(0,"", Coord(0.0,0.0),""),cod = "",message = 0.0, cnt = 0, list = emptyList())
}

