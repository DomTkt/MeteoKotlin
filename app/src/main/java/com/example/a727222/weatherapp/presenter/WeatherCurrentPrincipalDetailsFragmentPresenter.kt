package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import com.example.a727222.weatherapp.network.ApiServiceRx
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class WeatherCurrentPrincipalDetailsFragmentPresenter {

    var listener : WeatherCurrentPrincipalDetailsFragmentPresenterListener?
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?, view : WeatherCurrentPrincipalDetailsFragmentPresenterListener?){

        this.searchCity = searchCity
        this.listener = view
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }

    fun updateWeatherCurrentPrincipalDetailsData(){

        ApiServiceRx().getObservableWeatherCurrent()
                .subscribeBy (
                        onNext = {
                            listener?.setWeatherCurrentPrincipalDetailsData(it)
                        },
                        onError =  { it.printStackTrace() },
                        onComplete = { println("Done!") }
                )
    }

    fun updateWeatherCurrentPrincipalDetailsDataSearch(){

        ApiServiceRx().getObservableWeatherCurrentSearch(searchCity)
                .subscribeBy (
                        onNext = {
                            listener?.setWeatherCurrentPrincipalDetailsData(it)
                        },
                        onError =  {
                            updateWeatherCurrentPrincipalDetailsData()
                            it.printStackTrace() },
                        onComplete = { println("Done!") }
                )

    }

    interface WeatherCurrentPrincipalDetailsFragmentPresenterListener {

        fun setWeatherCurrentPrincipalDetailsData(weatherCurrent: WeatherCurrent?)

    }
}