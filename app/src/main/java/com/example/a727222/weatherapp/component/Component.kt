package com.example.a727222.weatherapp.component

import com.example.a727222.weatherapp.module.Module
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import com.example.a727222.weatherapp.presenter.WeatherCurrentPrincipalDetailsFragmentPresenter
import com.example.a727222.weatherapp.presenter.WeatherForecastListFragmentPresenter
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(Module::class))
interface Component {
    fun inject(weatherCurrent: WeatherActivity)
    fun plus(weatherCurrentAdditionalDetailsFragmentPresenter: WeatherCurrentAdditionalDetailsFragmentPresenter)
    fun plus(weatherCurrentPrincipalDetailsFragmentPresenter: WeatherCurrentPrincipalDetailsFragmentPresenter)
    fun plus(weatherForecastListFragmentPresenter: WeatherForecastListFragmentPresenter)
}