package com.example.a727222.weatherapp.presenter

import android.content.Context
import android.content.res.Configuration
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.component.DaggerComponentWeatherForecastList
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class WeatherForecastListFragmentPresenter(private var context: Context) : IA {

    override val weatherForecastListData: PublishSubject<WeatherForecast?>

    @Inject
    lateinit var networkingRx : NetworkingRx
    private var weatherForecastTrunc : WeatherForecast
    private val nbRowLandscape : Int
    private val nbRowPortrait : Int


    init {
        weatherForecastTrunc = WeatherForecast()
        DaggerComponentWeatherForecastList.builder().moduleWeatherForecastList(ModuleWeatherForecastList(context)).build().plus(this)
        nbRowLandscape = context.resources.getInteger(R.integer.nb_row_list_weather_forecast_landscape)
        nbRowPortrait = context.resources.getInteger(R.integer.nb_row_list_weather_forecast_portrait)
        weatherForecastListData = PublishSubject.create()
    }

    override fun updateWeatherForecastListSearch(searchCity: String?){
        networkingRx.getObservableWeatherForecastSearch(searchCity)
                .subscribe( { weatherForecast : WeatherForecast ->
                    truncListWeather(weatherForecast,nbRowLandscape,nbRowPortrait)
                    weatherForecastListData.onNext(weatherForecastTrunc)
                }, { throwable ->
                    weatherForecastListData.onError(throwable)
                })
    }

    override fun truncListWeather(weatherForecast : WeatherForecast,truncLandscape : Int, truncPortrait : Int){

        weatherForecastTrunc = weatherForecast
        val orientation = context.resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            weatherForecastTrunc?.list = weatherForecastTrunc?.list?.take(truncLandscape)
        }else {
            weatherForecastTrunc?.list = weatherForecastTrunc?.list?.take(truncPortrait)
        }
    }
}