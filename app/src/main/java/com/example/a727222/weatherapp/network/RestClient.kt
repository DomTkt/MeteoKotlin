package com.example.a727222.weatherapp.network

import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.IWeatherItemAPIServices
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestClient : Networking {

    override fun getCurrentWeather(callback: IApiResponse<WeatherCurrent>) {
        val currentWeather = getClient()?.weatherCurrent("Lyon", "d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        currentWeather?.enqueue(object : Callback<WeatherCurrent>{
            override fun onFailure(call: Call<WeatherCurrent>, t: Throwable) {

            }

            override fun onResponse(call: Call<WeatherCurrent>, response: Response<WeatherCurrent>) {
                callback.onSuccess(response.body())
            }
        })
    }

    override fun getWeatherForecastForOneWeek(callback: IApiResponse<WeatherForecast>) {
        val forecastWeather = getClient()?.weatherForecast("Lyon", "d5ec8c8ab7f0c4dae27e70d1a9ab10cf")
        forecastWeather?.enqueue(object : Callback<WeatherForecast>{
            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {

            }

            override fun onResponse(call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                callback.onSuccess(response.body())
            }
        })
    }

    override fun getWeatherForecastForOneWeekSearch(callback: IApiResponse<WeatherForecast>, searchCity: String?) {
        val forecastWeather = getClient()?.weatherForecastSearch(searchCity)
        forecastWeather?.enqueue(object : Callback<WeatherForecast>{
            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {

            }

            override fun onResponse(call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                callback.onSuccess(response.body())
            }
        })
    }

    override fun getCurrentWeatherSearch(callback : IApiResponse<WeatherCurrent>, searchCity : String?) {
        val currentWeather = getClient()?.weatherCurrentSearch(searchCity)
        currentWeather?.enqueue(object : Callback<WeatherCurrent> {
                    override fun onResponse(call: Call<WeatherCurrent>, response: Response<WeatherCurrent>) {
                        callback.onSuccess(response.body())
                    }
                    override fun onFailure(call: Call<WeatherCurrent>, t: Throwable) {

                    }
        })
    }

    private var mRestService: IWeatherItemAPIServices? = null

    private fun getClient(): IWeatherItemAPIServices? {
        if (mRestService == null) {

            val url = "http://api.openweathermap.org"
            val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            mRestService = retrofit.create(IWeatherItemAPIServices::class.java)
        }
        return mRestService
    }

}