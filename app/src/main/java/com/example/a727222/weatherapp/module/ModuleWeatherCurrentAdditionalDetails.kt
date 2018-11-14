package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleWeatherCurrentAdditionalDetails(context: Context, searchCity : String?, listener : WeatherCurrentAdditionalDetailsFragmentPresenter.WeatherCurrentAdditionalDetailsFragmentPresenterListener?) : ModuleBase(context) {
    private var context: Context
    private var searchCity : String?
    private var listener : WeatherCurrentAdditionalDetailsFragmentPresenter.WeatherCurrentAdditionalDetailsFragmentPresenterListener?

    init{
        this.context = context
        this.searchCity = searchCity
        this.listener = listener
    }

    @Provides
    @Singleton
    fun provideWeatherCurrentAdditionalDetailsFragmentPresenter() : WeatherCurrentAdditionalDetailsFragmentPresenter {
        return WeatherCurrentAdditionalDetailsFragmentPresenter(context,searchCity,listener)
    }
}