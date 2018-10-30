package com.example.a727222.weatherapp.manager

import android.content.Context
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast

class DataManager {

    private var networker : Networking?
    var context : Context

    constructor(context: Context, networker : Networking?){
        this.context = context
        this.networker = networker
    }


    fun getCurrentWeather(callback : IApiResponse<WeatherCurrent>){
        networker?.getCurrentWeather(callback)
    }

    fun getCurrentWeatherSearch(callback : IApiResponse<WeatherCurrent>, searchCity : String?){
        networker?.getCurrentWeatherSearch(callback, searchCity)
    }

    fun getWeatherForecastForOneWeek(callback : IApiResponse<WeatherForecast>){
        networker?.getWeatherForecastForOneWeek(callback)
    }

    fun getWeatherForecastForOneWeekSearch(callback : IApiResponse<WeatherForecast>, searchCity : String?){
        val listWeatherForecast = networker?.getWeatherForecastForOneWeekSearch(callback,searchCity)
    }

//
//    fun getWeatherForecastDayDetail(callback : IApiResponse<WeatherForecastDay>, position : Int) : Call<WeatherForecast>{
//        val listWeatherForecast = networker.listWeatherForecast()
//        listWeatherForecast.enqueue(object : Callback<WeatherForecast>
//        {
//            override fun onResponse(call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
//                callback.onSuccess(response.body()?.list?.get(position))
//            }
//
//            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
//                callback.onError(t)
//            }
//        }
//        )
//        return listWeatherForecast
//    }



}