package com.example.a727222.weatherapp.module

import android.content.Context
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.network.MockClient
import com.example.a727222.weatherapp.network.RestClient
import com.example.a727222.weatherapp.presenter.WeatherForecastListFragmentPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ModuleWeatherForecastList(context: Context, searchCity : String?, view : WeatherForecastListFragmentPresenter.View?){
    private var context: Context
    private var searchCity : String?
    private var view : WeatherForecastListFragmentPresenter.View?

    init{
        this.context = context
        this.searchCity = searchCity
        this.view = view
    }

    @Provides
    @Singleton
    fun provideNetworker(): Networking {
        when(context.packageName)
        {
            "com.example.a727222.weatherapp.mock" -> return MockClient(context)
            "com.example.a727222.weatherapp.prod" -> return RestClient()
            else -> return MockClient(context)
        }
    }

    @Provides
    @Singleton
    fun provideIA() : IA {
        return WeatherForecastListFragmentPresenter(context,searchCity,view)
    }

    @Provides
    @Singleton
    fun provideWeatherForecastListFragmentPresenter() : WeatherForecastListFragmentPresenter {
        return WeatherForecastListFragmentPresenter(context,searchCity,view)
    }
}