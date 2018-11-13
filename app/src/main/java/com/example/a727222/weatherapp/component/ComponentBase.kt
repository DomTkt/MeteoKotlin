package com.example.a727222.weatherapp.component

import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.module.ModuleBase
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import com.example.a727222.weatherapp.presenter.WeatherCurrentPrincipalDetailsFragmentPresenter
import com.example.a727222.weatherapp.presenter.WeatherForecastListFragmentPresenter
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ModuleBase::class))
interface ComponentBase {
    fun inject(weatherCurrent: WeatherActivity)
    fun plus(weatherCurrentAdditionalDetailsFragmentPresenter: WeatherCurrentAdditionalDetailsFragmentPresenter)
    fun plus(weatherCurrentPrincipalDetailsFragmentPresenter: WeatherCurrentPrincipalDetailsFragmentPresenter)
    fun plus(weatherForecastListFragmentPresenter: WeatherForecastListFragmentPresenter)
    fun plus(ia: IA)
}