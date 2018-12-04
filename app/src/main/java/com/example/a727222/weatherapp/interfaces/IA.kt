package com.example.a727222.weatherapp.interfaces

import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.subjects.PublishSubject

//Create this class to implement in presenter to Inject with Dagger
interface IA {
    val weatherForecastListData : PublishSubject<WeatherForecast?>
    fun updateWeatherForecastListSearch(searchCity : String?)
    fun truncListWeather(weatherForecast : WeatherForecast, truncLandscape : Int, truncPortrait : Int)
}