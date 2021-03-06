package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.presenter.WeatherCurrentPrincipalDetailsFragmentPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This class does injection processing by creating unique instances of objects call
 *
 */

@Module
class ModuleWeatherCurrentPrincipalDetails(context: Context) : ModuleBase(context){
    private var context: Context

    init{
        this.context = context
    }

    @Provides
    @Singleton
    fun provideWeatherCurrentPrincipalDetailsFragmentPresenter() : WeatherCurrentPrincipalDetailsFragmentPresenter {
        return WeatherCurrentPrincipalDetailsFragmentPresenter(context)
    }
}