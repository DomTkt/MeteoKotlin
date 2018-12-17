package com.example.a727222.weatherapp.network

import android.content.Context
import com.example.a727222.weatherapp.interfaces.IWeatherItemAPIServicesRx
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * @param context
 * This class allows to get the result of network calls
 */
class ApiServiceRx(context : Context) : NetworkingRx{

    private fun apiService() = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org")
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(IWeatherItemAPIServicesRx::class.java)

    /**
     * getObservableWeatherCurrentSearch allows to get the weather current of the city search
     * @param city The city search by the user
     * @return Observable<WeatherCurrent>
     */
    override fun getObservableWeatherCurrentSearch(city : String?) : Observable<WeatherCurrent>{
        return getObservableGenericSearchFromResponse(apiService().weatherCurrentSearch(city))
    }

    /**
     * getObservableWeatherForecastSearch allows to get the weather forecast of the city search
     * @param city The city search by the user
     * @return Observable<WeatherForecast>
     */
    override fun getObservableWeatherForecastSearch(city : String?) : Observable<WeatherForecast>{
        return getObservableGenericSearchFromResponse(apiService().weatherForecastSearch(city))
    }

    /**
     * @param T the type of the model that we want the response
     */
    private fun <T> getObservableGenericSearchFromResponse(observable: Observable<Response<T>>) : Observable<T>{

        return observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map { result ->
                    if (result.raw().cacheResponse() != null){
                        result.body()

                    }else{
                        result.body()
                    }
                }
    }

    /**
     * Intercept the network cache
     */
    val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        var cacheControl = CacheControl.Builder()
                .maxAge(1, TimeUnit.MINUTES)
                .build()

        response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
    }

    /**
     * the size of the maximum cache
     */
    val cacheSize = 10 * 1024 * 1024
    /**
     * directory of the cache
     */
    val httpCacheDirectory = File(context.cacheDir, "http-cache")
    /**
     * cache
     */
    val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    val httpClient = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .build()



}