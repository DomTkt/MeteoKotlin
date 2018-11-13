package com.example.a727222.weatherapp.presenter

import android.content.Context
import android.content.res.Configuration
import com.example.a727222.weatherapp.component.DaggerComponentWeatherForecastList
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import javax.inject.Inject

class WeatherForecastListFragmentPresenter : IA {

    var view : View?
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?
    private var context : Context
    private var weatherForecastTrunc : WeatherForecast?

    constructor(context : Context, searchCity : String?, view :View?){
        //DaggerComponent.builder().module(ModuleBase(context)).build().plus(this)
        this.searchCity = searchCity
        this.view = view
        this.context = context
        weatherForecastTrunc = WeatherForecast()
        DaggerComponentWeatherForecastList.builder().moduleWeatherForecastList(ModuleWeatherForecastList(context,searchCity,view)).build().plus(this)
    }

    override fun updateWeatherForecastList(){
        networking.getWeatherForecastForOneWeek(object : IApiResponse<WeatherForecast>
        {
            override fun onSuccess(obj: WeatherForecast?) {
                truncListWeather(obj,3,7)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
        )
    }

    override fun updateWeatherForecastListSearch(){

        networking.getWeatherForecastForOneWeekSearch(object : IApiResponse<WeatherForecast> {
            override fun onSuccess(obj: WeatherForecast?) {
                //If the name of the city is unknown by the weather API...
                if(obj != null) {
                    truncListWeather(obj,3,7)
                }else{
                    updateWeatherForecastList()
                }
            }
            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }
//TODO fichier de values
    override fun truncListWeather(weatherForecast : WeatherForecast?,truncLandscape : Int, truncPortrait : Int){
    //refracto
        weatherForecastTrunc = weatherForecast
        var orientation = context.resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            weatherForecastTrunc?.list = weatherForecastTrunc?.list?.take(truncLandscape)
            view?.setForecastItem(weatherForecastTrunc)
        }else {
            weatherForecastTrunc?.list = weatherForecastTrunc?.list?.take(truncPortrait)
            view?.setForecastItem(weatherForecastTrunc)
        }
    }


    interface View{
        fun setForecastItem(weatherForecast : WeatherForecast?)
    }
}