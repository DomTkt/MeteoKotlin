package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * This class obtains the data to display for the principal data
 */
class WeatherCurrentPrincipalDetailsFragmentPresenter(private var context: Context) {

    /**
     * networkingRx contains isntance of NetworkingRx, which according to the configuration of the variant variant change
     */
    @Inject
    lateinit var networkingRx : NetworkingRx
    //Stock the result of the network call and publish in the view for the principal data
    var weatherCurrentPrincipalDetailsData : PublishSubject<WeatherCurrent>

    init {
        weatherCurrentPrincipalDetailsData = PublishSubject.create()
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }

    fun updateWeatherCurrentPrincipalDetailsDataSearch(searchCity: String?){

        networkingRx.getObservableWeatherCurrentSearch(searchCity)
                ?.subscribe( { weatherCurrent : WeatherCurrent ->
                    weatherCurrentPrincipalDetailsData.onNext(weatherCurrent)
                }, { throwable ->
                    weatherCurrentPrincipalDetailsData.onError(throwable)
                })

    }
}