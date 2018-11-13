package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import javax.inject.Inject

class WeatherCurrentPrincipalDetailsFragmentPresenter {

    var listener : WeatherCurrentPrincipalDetailsFragmentPresenterListener?
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?, view : WeatherCurrentPrincipalDetailsFragmentPresenterListener?){

        this.searchCity = searchCity
        this.listener = view
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }

    fun updateWeatherCurrentPrincipalDetailsData(){
        networking.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                listener?.setWeatherCurrentPrincipalDetailsData(obj)
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
                    listener?.setWeatherCurrentPrincipalDetailsData(obj)
                }else{
                    updateWeatherCurrentPrincipalDetailsData()
                }

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }



    interface WeatherCurrentPrincipalDetailsFragmentPresenterListener {

        fun setWeatherCurrentPrincipalDetailsData(weatherCurrent: WeatherCurrent?)

    }
}