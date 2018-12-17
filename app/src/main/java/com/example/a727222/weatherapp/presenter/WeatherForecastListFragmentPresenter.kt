package com.example.a727222.weatherapp.presenter

import android.content.Context
import android.content.res.Configuration
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.component.DaggerComponentWeatherForecastList
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.NetworkingRx
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * This class obtains the data to display for the forecast data
 */
class WeatherForecastListFragmentPresenter(private var context: Context) : IA {

    //Stock the result of the network call and publish in the view for the forecast data
    override val weatherForecastListData: PublishSubject<WeatherForecast?>

    /**
     * networkingRx contains isntance of NetworkingRx, which according to the configuration of the variant variant change
     */
    @Inject
    lateinit var networkingRx : NetworkingRx
    /**
     * weatherForecastTrunc data weatherForecast trunc according to the orientation
     */
    private var weatherForecastTrunc : WeatherForecast
    /**
     * number of row of weather forecast when in mode landscape
     */
    private val nbRowLandscape : Int
    /**
     * number of row of weather forecast when in mode portrait
     */
    private val nbRowPortrait : Int


    init {
        weatherForecastTrunc = WeatherForecast()
        DaggerComponentWeatherForecastList.builder().moduleWeatherForecastList(ModuleWeatherForecastList(context)).build().plus(this)
        nbRowLandscape = context.resources.getInteger(R.integer.nb_row_list_weather_forecast_landscape)
        nbRowPortrait = context.resources.getInteger(R.integer.nb_row_list_weather_forecast_portrait)
        weatherForecastListData = PublishSubject.create()
    }

    override fun updateWeatherForecastListSearch(searchCity: String?){
        networkingRx.getObservableWeatherForecastSearch(searchCity)
                .subscribe( { weatherForecast : WeatherForecast ->
                    truncListWeather(weatherForecast,nbRowLandscape,nbRowPortrait)
                    weatherForecastListData.onNext(weatherForecastTrunc)
                }, { throwable ->
                    weatherForecastListData.onError(throwable)
                })
    }

    /**
     * @param weatherForecast
     * @param truncLandscape
     * @param truncPortrait
     * trunc the number of element of the weatherForecast according to the orientation
     */
    override fun truncListWeather(weatherForecast : WeatherForecast,truncLandscape : Int, truncPortrait : Int){
        weatherForecastTrunc = weatherForecast
        val orientation = context.resources.configuration.orientation
        if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            weatherForecastTrunc?.list = weatherForecastTrunc?.list?.take(truncLandscape)
        }else {
            weatherForecastTrunc?.list = weatherForecastTrunc?.list?.take(truncPortrait)
        }
    }
}