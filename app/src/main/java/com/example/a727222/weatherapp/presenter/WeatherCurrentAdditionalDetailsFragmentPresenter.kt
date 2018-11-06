package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponent
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.Module
import javax.inject.Inject

class WeatherCurrentAdditionalDetailsFragmentPresenter {

    var view : View
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?,view : View){
        DaggerComponent.builder().module(Module(context)).build().plus(this)
        this.searchCity = searchCity
        this.view = view
    }

    fun updateWeatherCurrentAdditionalDetailsData(){
        networking.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                view.setWeatherCurrentAdditionalDetailsData(obj)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
        )
    }

    fun updateWeatherCurrentAdditionalDetailsDataSearch(){

        networking.getCurrentWeatherSearch(object : IApiResponse<WeatherCurrent>{
            override fun onSuccess(obj: WeatherCurrent?) {
                //If the name of the city is unknow by the weather API...
                if(obj != null) {
                    view.setWeatherCurrentAdditionalDetailsData(obj)
                }else{
                    updateWeatherCurrentAdditionalDetailsData()
                }

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }


    interface View {

        fun setWeatherCurrentAdditionalDetailsData(weatherCurrent: WeatherCurrent?)

    }
}