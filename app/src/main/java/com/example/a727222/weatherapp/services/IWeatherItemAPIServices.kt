package com.example.a727222.weatherapp.services

import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherItemAPIServices {
        @GET("/data/2.5/weather?q=Lyon&APPID=d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        fun listWeatherCurrent(): Call<WeatherCurrent>

        @GET("/data/2.5/forecast/daily?q=Lyon,FR&appid=d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        fun listWeatherForecast(): Call<WeatherForecast>

        @GET("/data/2.5/weather?APPID=d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        fun listWeatherCurrentSearch(@Query("q") q : String?): Call<WeatherCurrent>

        @GET("/data/2.5/forecast/daily?appid=d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        fun listWeatherForecastSearch(@Query("q") q : String?): Call<WeatherForecast>

}