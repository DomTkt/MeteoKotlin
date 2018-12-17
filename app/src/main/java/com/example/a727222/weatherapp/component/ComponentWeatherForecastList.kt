package com.example.a727222.weatherapp.component

import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import com.example.a727222.weatherapp.presenter.WeatherForecastListFragmentPresenter
import com.example.a727222.weatherapp.view.fragment.WeatherForecastListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * This class allows to specify the classes where we will make the injection of dependency
 */

@Singleton
@Component(modules = arrayOf(ModuleWeatherForecastList::class))
interface ComponentWeatherForecastList {
    fun plus(weatherForecastListFragment: WeatherForecastListFragment)
    fun plus(weatherForecastListFragmentPresenter : WeatherForecastListFragmentPresenter)
    fun plus(ia : IA)
}