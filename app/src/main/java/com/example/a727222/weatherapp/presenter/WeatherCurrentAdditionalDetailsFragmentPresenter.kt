package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject



class WeatherCurrentAdditionalDetailsFragmentPresenter
(private var context: Context) {

    @Inject
    lateinit var networkingRx : NetworkingRx
    //stocker le resultat d'un appel réseau et de le publier a mes vues
    var weatherCurrentAdditionalDetailsData : PublishSubject<WeatherCurrent>

    init {
        weatherCurrentAdditionalDetailsData = PublishSubject.create()
        //Inject
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }
    fun updateWeatherCurrentAdditionalDetailsDataSearch(searchCity : String?){

        networkingRx.getObservableWeatherCurrentSearch(searchCity)
                ?.subscribe( { weatherCurrent : WeatherCurrent ->
        weatherCurrentAdditionalDetailsData.onNext(weatherCurrent)
        }, { throwable ->
            weatherCurrentAdditionalDetailsData.onError(throwable)
        })

    }
}
