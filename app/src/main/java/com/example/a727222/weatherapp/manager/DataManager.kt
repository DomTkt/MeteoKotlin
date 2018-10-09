package com.example.a727222.weatherapp.manager

import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.services.IWeatherItemAPIServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataManager {

    fun create(): IWeatherItemAPIServices {
        val url = "http://api.openweathermap.org"
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        return retrofit.create(IWeatherItemAPIServices::class.java)
    }

    fun getCurrentWeather(callback : IApiResponse<WeatherCurrent>) : Call<WeatherCurrent> {
        val listCurrentWeather = create().listWeatherCurrent()
        listCurrentWeather.enqueue(object : Callback<WeatherCurrent> {
            override fun onResponse(call: Call<WeatherCurrent>, response: Response<WeatherCurrent>) {
                callback.onSuccess(response.body())
            }
            override fun onFailure(call: Call<WeatherCurrent>, t: Throwable) {
                callback.onError(t)
            }
        })
        return listCurrentWeather
    }

}