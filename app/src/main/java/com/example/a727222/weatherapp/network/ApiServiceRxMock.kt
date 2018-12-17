package com.example.a727222.weatherapp.network

import android.content.Context
import com.example.a727222.weatherapp.interfaces.IWeatherItemAPIServicesRx
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This class allows to obtains a mock of data for a search weather
 */
class ApiServiceRxMock(context : Context) : NetworkingRx{

    /**
     * context contains the actual context
     */
    var context : Context

    init{
        this.context = context
    }

    private var mRestService: IWeatherItemAPIServicesRx? = null

    fun apiService(context: Context): IWeatherItemAPIServicesRx? {
        if (mRestService == null) {
            val client = OkHttpClient.Builder()
                    .addInterceptor(FakeInterceptor(context))
                    .build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("http://mock")
                    .client(client)
                    .build()

            mRestService = retrofit.create(IWeatherItemAPIServicesRx::class.java)
        }
        return mRestService
    }


    /**
     * getObservableWeatherCurrentSearch allows to get the weather current of the city search
     * @param city The city search by the user
     * @return Observable<WeatherCurrent>
     */
    override fun getObservableWeatherCurrentSearch(city : String?) : Observable<WeatherCurrent>{

        return getObservableGenericSearchFromResponse(apiService(context)?.weatherCurrentSearch(city))
    }

    /**
     * getObservableWeatherForecastSearch allows to get the weather forecast of the city search
     * @param city The city search by the user
     * @return Observable<WeatherForecast>
     */
    override fun getObservableWeatherForecastSearch(city : String?) : Observable<WeatherForecast>{
        return getObservableGenericSearchFromResponse(apiService(context)?.weatherForecastSearch(city))
    }

    /**
     * get Observable type from a specific response
     * @param T the type of the model that we want the response
     */
    private fun <T> getObservableGenericSearchFromResponse(observable: Observable<Response<T>>?) : Observable<T>{

        return Observable.create({emitter ->
            observable?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribeOn(Schedulers.io())
                    ?.map { result ->
                        result.body()
                    }
                    ?.subscribe({ t: T? ->
                        if (t != null) {
                            emitter.onNext(t)
                        }
                    }, {
                        throwable -> println(throwable.message)
                    }

                    )
        })
    }


}