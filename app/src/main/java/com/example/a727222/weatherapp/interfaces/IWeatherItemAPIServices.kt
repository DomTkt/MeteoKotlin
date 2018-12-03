package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherItemAPIServices {

        @GET("/data/2.5/weather")
        fun weatherCurrent(@Query("q") city: String,
        @Query("APPID") appId: String): Call<WeatherCurrent>

        @GET("/data/2.5/forecast/daily")
        fun weatherForecast(@Query("q") city: String,
        @Query("APPID") appId: String): Call<WeatherForecast>

        @GET("/data/2.5/weather?APPID=d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        fun weatherCurrentSearch(@Query("q") q : String?): Call<WeatherCurrent>

        @GET("/data/2.5/forecast/daily?appid=d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        fun weatherForecastSearch(@Query("q") q : String?): Call<WeatherForecast>

}