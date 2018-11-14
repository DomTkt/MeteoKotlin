package com.example.a727222.weatherapp.component

import com.example.a727222.weatherapp.module.ModuleWeatherCurrentAdditionalDetails
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import com.example.a727222.weatherapp.view.fragment.WeatherCurrentAdditionalDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ModuleWeatherCurrentAdditionalDetails::class))
interface ComponentWeatherCurrentAdditionalDetails {
    fun plus(weatherCurrentAdditionalDetailsFragmentPresenter: WeatherCurrentAdditionalDetailsFragmentPresenter)
    fun plus(weatherCurrentAdditionalDetailsFragment: WeatherCurrentAdditionalDetailsFragment)

}