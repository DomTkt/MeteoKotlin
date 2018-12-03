package com.example.a727222.weatherapp.presenter

import android.content.Context
import com.example.a727222.weatherapp.component.DaggerComponentBase
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleBase
import com.example.a727222.weatherapp.network.ApiServiceRx
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject



class WeatherCurrentAdditionalDetailsFragmentPresenter {

    var listener : WeatherCurrentAdditionalDetailsFragmentPresenterListener?
    @Inject
    lateinit var networking : Networking
    private var searchCity : String?

    constructor(context : Context, searchCity : String?,listener : WeatherCurrentAdditionalDetailsFragmentPresenterListener?){

        this.searchCity = searchCity
        this.listener = listener
        //Inject
        DaggerComponentBase.builder().moduleBase(ModuleBase(context)).build().plus(this)
    }

    fun updateWeatherCurrentAdditionalDetailsData(){

        ApiServiceRx().getObservableWeatherCurrent()
                .subscribeBy (
                    onNext = {
                        listener?.setWeatherCurrentAdditionalDetailsData(it)
                    },
                    onError =  { it.printStackTrace() },
                    onComplete = { println("Done!") }
                )
    }

    fun updateWeatherCurrentAdditionalDetailsDataSearch(){

        ApiServiceRx().getObservableWeatherCurrentSearch(searchCity)
                .subscribeBy (
                        onNext = {
                            listener?.setWeatherCurrentAdditionalDetailsData(it)
                       },
                        onError =  {
                            updateWeatherCurrentAdditionalDetailsData()
                            it.printStackTrace() },
                        onComplete = { println("Done!") }
                )
    }

    //Link to view
    interface WeatherCurrentAdditionalDetailsFragmentPresenterListener {

        fun setWeatherCurrentAdditionalDetailsData(weatherCurrent: WeatherCurrent?)

    }
}