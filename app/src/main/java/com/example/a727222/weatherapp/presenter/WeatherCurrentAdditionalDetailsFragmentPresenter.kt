package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import com.example.a727222.weatherapp.network.ApiServiceRx
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject



class WeatherCurrentAdditionalDetailsFragmentPresenter {

    @Inject
    lateinit var networking : Networking
    var weatherCurrentAdditionalDetailsData : PublishSubject<WeatherCurrent?>

    constructor(context : Context){
        weatherCurrentAdditionalDetailsData = PublishSubject.create()
        //Inject
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }
    fun updateWeatherCurrentAdditionalDetailsDataSearch(searchCity : String?){

        ApiServiceRx().getObservableWeatherCurrentSearch(searchCity)
                .subscribe( { weatherCurrent : WeatherCurrent ->
        weatherCurrentAdditionalDetailsData.onNext(weatherCurrent)
        }, { throwable ->
            weatherCurrentAdditionalDetailsData.onError(throwable)
        })

    }
}
