package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponent
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.Module
import javax.inject.Inject

class WeatherCurrentPrincipalDetailsFragmentPresenter {

    var view : View
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?, view : View){
        DaggerComponent.builder().module(Module(context)).build().plus(this)
        this.searchCity = searchCity
        this.view = view
    }

    fun updateWeatherCurrentPrincipalDetailsData(){
        networking.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                view.setWeatherCurrentPrincipalDetailsData(obj)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
        )
    }

    fun updateWeatherCurrentPrincipalDetailsDataSearch(){

        networking.getCurrentWeatherSearch(object : IApiResponse<WeatherCurrent> {
            override fun onSuccess(obj: WeatherCurrent?) {
                //If the name of the city is unknow by the weather API...
                if(obj != null) {
                    view.setWeatherCurrentPrincipalDetailsData(obj)
                }else{
                    updateWeatherCurrentPrincipalDetailsData()
                }

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }



    interface View {

        fun setWeatherCurrentPrincipalDetailsData(weatherCurrent: WeatherCurrent?)

    }
}