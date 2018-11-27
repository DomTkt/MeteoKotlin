package com.example.a727222.weatherapp.network

import com.example.a727222.weatherapp.interfaces.IWeatherItemAPIServicesRx
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServiceRx{
    private fun apiService() = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(IWeatherItemAPIServicesRx::class.java)


    fun getObservableWeatherCurrent() : Observable<WeatherCurrent>{
        return getObservableGeneric(apiService().weatherCurrent("Lyon","d5ec8c8ab7f0c4dae27e70d1a9ab10cf"))
    }

    fun getObservableWeatherForecast() : Observable<WeatherForecast>{
        return getObservableGeneric(apiService().weatherForecast("Lyon","d5ec8c8ab7f0c4dae27e70d1a9ab10cf"))
    }

    fun getObservableWeatherCurrentSearch(city : String?) : Observable<WeatherCurrent>{
        return getObservableGeneric(apiService().weatherCurrentSearch(city))
    }

    fun getObservableWeatherForecastSearch(city : String?) : Observable<WeatherForecast>{
        return getObservableGeneric(apiService().weatherForecastSearch(city))
    }


    private fun <T> getObservableGeneric(observable : Observable<T>) : Observable<T>
    {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

}