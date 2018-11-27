package com.example.a727222.weatherapp.presenter

import android.content.Context
import android.content.res.Configuration
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.component.DaggerComponentWeatherForecastList
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import com.example.a727222.weatherapp.network.ApiServiceRx
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class WeatherForecastListFragmentPresenter(private var context: Context, private var searchCity: String?, var view: View?) : IA {

    @Inject
    lateinit var networking : Networking
    private var weatherForecastTrunc : WeatherForecast?
    private val nbRowLandscape : Int
    private val nbRowPortrait : Int

    init {
        weatherForecastTrunc = WeatherForecast()
        DaggerComponentWeatherForecastList.builder().moduleWeatherForecastList(ModuleWeatherForecastList(context,searchCity,view)).build().plus(this)
        nbRowLandscape = context.resources.getInteger(R.integer.nb_row_list_weather_forecast_landscape)
        nbRowPortrait = context.resources.getInteger(R.integer.nb_row_list_weather_forecast_portrait)
    }

    override fun updateWeatherForecastList(){

        ApiServiceRx().getObservableWeatherForecast()
                .subscribeBy(
                        onNext = {
                            truncListWeather(it,nbRowLandscape,nbRowPortrait)
                        },
                        onError =  { it.printStackTrace() },
                        onComplete = { println("Done!") }
                )
    }

    override fun updateWeatherForecastListSearch(){
        ApiServiceRx().getObservableWeatherForecastSearch(searchCity)
                .subscribeBy (
                        onNext = {
                                truncListWeather(it,nbRowLandscape,nbRowPortrait)
                        },
                        onError =  {
                            updateWeatherForecastList()
                            it.printStackTrace() },
                        onComplete = { println("Done!") }
                )
    }

    override fun truncListWeather(weatherForecast : WeatherForecast?,truncLandscape : Int, truncPortrait : Int){

        weatherForecastTrunc = weatherForecast
        val orientation = context.resources.configuration.orientation
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