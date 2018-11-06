package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponent
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.module.Module
import javax.inject.Inject

class WeatherForecastListFragmentPresenter {

    var view : View
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?, view :View){
        DaggerComponent.builder().module(Module(context)).build().plus(this)
        this.searchCity = searchCity
        this.view = view
    }

    fun updateWeatherForecastList(){
        networking.getWeatherForecastForOneWeek(object : IApiResponse<WeatherForecast>
        {
            override fun onSuccess(obj: WeatherForecast?) {
                view.setForecastItem(obj)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
        )
    }

    fun updateWeatherForecastListSearch(){

        networking.getWeatherForecastForOneWeekSearch(object : IApiResponse<WeatherForecast> {
            override fun onSuccess(obj: WeatherForecast?) {
                //If the name of the city is unknown by the weather API...
                if(obj != null) {
                    view.setForecastItem(obj)
                }else{
                    updateWeatherForecastList()
                }

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }


    interface View{
        fun setForecastItem(weatherForecast : WeatherForecast?)
    }
}