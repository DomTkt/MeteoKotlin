package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.presenter.WeatherForecastListFragmentPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleWeatherForecastList(context: Context) : ModuleBase(context){
    private var context: Context

    init{
        this.context = context
    }

    @Provides
    @Singleton
    fun provideIA() : IA {
        return WeatherForecastListFragmentPresenter(context)
    }

    @Provides
    @Singleton
    fun provideWeatherForecastListFragmentPresenter() : WeatherForecastListFragmentPresenter {
        return WeatherForecastListFragmentPresenter(context)
    }
}