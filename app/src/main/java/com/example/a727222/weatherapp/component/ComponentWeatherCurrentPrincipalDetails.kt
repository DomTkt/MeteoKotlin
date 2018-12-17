package com.example.a727222.weatherapp.component

import com.example.a727222.weatherapp.module.ModuleWeatherCurrentPrincipalDetails
import com.example.a727222.weatherapp.view.fragment.WeatherCurrentPrincipalDetailsFragment
import dagger.Component
import javax.inject.Singleton


/**
 * This class allows to specify the classes where we will make the injection of dependency
 */

@Singleton
@Component(modules = arrayOf(ModuleWeatherCurrentPrincipalDetails::class))
interface ComponentWeatherCurrentPrincipalDetails {
    fun plus(weatherCurrentPrincipalDetailsFragment: WeatherCurrentPrincipalDetailsFragment)
}