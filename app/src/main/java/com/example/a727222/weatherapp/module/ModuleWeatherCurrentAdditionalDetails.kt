package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleWeatherCurrentAdditionalDetails(context: Context) : ModuleBase(context) {
    private var context: Context

    init{
        this.context = context
    }

    @Provides
    @Singleton
    fun provideWeatherCurrentAdditionalDetailsFragmentPresenter() : WeatherCurrentAdditionalDetailsFragmentPresenter {
        return WeatherCurrentAdditionalDetailsFragmentPresenter(context)
    }
}