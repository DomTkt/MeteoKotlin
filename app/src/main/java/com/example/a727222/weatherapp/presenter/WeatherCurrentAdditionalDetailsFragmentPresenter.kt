package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import javax.inject.Inject

class WeatherCurrentAdditionalDetailsFragmentPresenter {

    var listener : WeatherCurrentAdditionalDetailsFragmentPresenterListener?
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?,listener : WeatherCurrentAdditionalDetailsFragmentPresenterListener?){

        this.searchCity = searchCity
        this.listener = listener
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }

    fun updateWeatherCurrentAdditionalDetailsData(){
        networking.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                listener?.setWeatherCurrentAdditionalDetailsData(obj)
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
                    listener?.setWeatherCurrentAdditionalDetailsData(obj)
                }else{
                    updateWeatherCurrentAdditionalDetailsData()
                }

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }


    interface WeatherCurrentAdditionalDetailsFragmentPresenterListener {

        fun setWeatherCurrentAdditionalDetailsData(weatherCurrent: WeatherCurrent?)

    }
}