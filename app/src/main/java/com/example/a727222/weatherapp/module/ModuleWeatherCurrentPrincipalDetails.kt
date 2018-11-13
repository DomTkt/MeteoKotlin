package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.presenter.WeatherCurrentPrincipalDetailsFragmentPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleWeatherCurrentPrincipalDetails(context: Context, searchCity : String?, view : WeatherCurrentPrincipalDetailsFragmentPresenter.WeatherCurrentPrincipalDetailsFragmentPresenterListener?){
    private var context: Context
    private var searchCity : String?
    private var view : WeatherCurrentPrincipalDetailsFragmentPresenter.WeatherCurrentPrincipalDetailsFragmentPresenterListener?

    init{
        this.context = context
        this.searchCity = searchCity
        this.view = view
    }

    @Provides
    @Singleton
    fun provideWeatherCurrentPrincipalDetailsFragmentPresenter() : WeatherCurrentPrincipalDetailsFragmentPresenter {
        return WeatherCurrentPrincipalDetailsFragmentPresenter(context,searchCity,view)
    }
}