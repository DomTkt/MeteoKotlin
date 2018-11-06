package com.example.a727222.weatherapp.daggerTestDeleteAfter

import com.example.a727222.weatherapp.view.WeatherActivity
import com.example.a727222.weatherapp.view.WeatherCurrentAdditionalDetailsFragment
import com.example.a727222.weatherapp.view.WeatherCurrentPrincipalDetailsFragment
import com.example.a727222.weatherapp.view.WeatherForecastListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(MyModule::class))
interface MyComponent {

    fun inject(weatherCurrent: WeatherActivity)
    fun plus(weatherCurrentAdditionalDetailsFragment: WeatherCurrentAdditionalDetailsFragment)
    fun plus(weatherCurrentPrincipalDetailsFragment: WeatherCurrentPrincipalDetailsFragment)
    fun plus(weatherForecastListFragment: WeatherForecastListFragment)


}