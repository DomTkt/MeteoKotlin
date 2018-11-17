package com.example.a727222.weatherapp.presenter

import android.content.Intent
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.view.fragment.WeatherForecastListFragment

class WeatherForecastDetailsActivityPresenter {

    private var view : View
    /// why this intent ?
    private var intent : Intent

    constructor(intent : Intent,view : View ){
        this.view = view
        this.intent = intent
    }


    fun updateForecastDetailDay(){
        view.setForecastDetailDayData(intent.getSerializableExtra(WeatherForecastListFragment.WEATHER_FORECAST_DAY_EXTRA) as WeatherForecastDay)
    }
    // rename this interface
    interface View{
        fun setForecastDetailDayData(weatherForecastDay : WeatherForecastDay?)
    }
}