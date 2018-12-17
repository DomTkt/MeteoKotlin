package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.subjects.PublishSubject

/**
 * This class allows to implement methods and variable in presenter to inject with Dagger a instance of IA which will be use for the view
 */
interface IA {
    val weatherForecastListData : PublishSubject<WeatherForecast?>
    fun updateWeatherForecastListSearch(searchCity : String?)
    fun truncListWeather(weatherForecast : WeatherForecast, truncLandscape : Int, truncPortrait : Int)
}