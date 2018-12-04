package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import com.example.a727222.weatherapp.network.ApiServiceRx
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class WeatherCurrentPrincipalDetailsFragmentPresenter {

    @Inject
    lateinit var networking : Networking
    var weatherCurrentPrincipalDetailsData : PublishSubject<WeatherCurrent?>

    constructor(context : Context){
        weatherCurrentPrincipalDetailsData = PublishSubject.create()
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }

    fun updateWeatherCurrentPrincipalDetailsDataSearch(searchCity: String?){

        ApiServiceRx().getObservableWeatherCurrentSearch(searchCity)
                .subscribe( { weatherCurrent : WeatherCurrent ->
                    weatherCurrentPrincipalDetailsData.onNext(weatherCurrent)
                }, { throwable ->
                    weatherCurrentPrincipalDetailsData.onError(throwable)
                })

    }
}