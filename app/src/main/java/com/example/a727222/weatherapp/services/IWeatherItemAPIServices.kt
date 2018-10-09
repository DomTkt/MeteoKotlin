package com.example.a727222.weatherapp.services

import com.example.a727222.weatherapp.models.WeatherCurrent
import retrofit2.Call
import retrofit2.http.GET

interface IWeatherItemAPIServices {
        @GET("/data/2.5/weather?q=Lyon&APPID=aa88a046056aa5bb82551d5d3ba364d4")
        fun listWeatherCurrent(): Call<WeatherCurrent>
}