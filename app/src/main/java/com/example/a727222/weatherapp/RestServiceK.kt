package com.example.a727222.weatherapp

import com.example.a727222.weatherapp.models.ResponseData
import com.example.a727222.weatherapp.models.WeatherCurrent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestServiceK {

    @GET("/login")
    fun login(@Query("username") id: String,
                       @Query("pwd") pwd: String): Call<ResponseData>

    @GET("/data/2.5/weather")
    fun getCurrentWeather(@Query("q") city: String,
                                   @Query("APPID") appId: String): Call<WeatherCurrent>

}