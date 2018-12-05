package com.example.a727222.weatherapp.network

import android.content.Context
import com.example.a727222.weatherapp.interfaces.IWeatherItemAPIServicesRx
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


class ApiServiceRx(context : Context){

    private fun apiService() = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(IWeatherItemAPIServicesRx::class.java)

    fun getObservableWeatherCurrentSearch(city : String?) : Observable<WeatherCurrent>{


        return Observable.create({emitter ->

        apiService().weatherCurrentSearch(city)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { resultWeatherCurrentSearch ->
                    if (resultWeatherCurrentSearch.raw().cacheResponse() != null){
                         resultWeatherCurrentSearch.body()

                    }else{
                        resultWeatherCurrentSearch.body()
                    }
                }
                .subscribe({ t: WeatherCurrent? ->
                    if (t != null) {
                        emitter.onNext(t)
                    }

        }, {
            throwable -> println(throwable.message)
        }

        )
        })
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

    val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        var cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

        response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
    }

    val cacheSize = 10 * 1024 * 1024
    val httpCacheDirectory = File(context.cacheDir, "http-cache")
    val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    val httpClient = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .build()



}