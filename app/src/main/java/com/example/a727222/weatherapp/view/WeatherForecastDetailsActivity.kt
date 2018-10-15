package com.example.a727222.weatherapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.manager.DataManager
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.utils.Utils

class WeatherForecastDetailsActivity : AppCompatActivity() {

    private lateinit var weatherDetailDayTextView : TextView
    private lateinit var weatherDetailCityTextView : TextView
    private lateinit var weatherDetailTemperatureTextView : TextView

    private lateinit var weatherDetailTemperatureNightTextView : TextView
    private lateinit var weatherDetailTemperatureMorningTextView : TextView
    private lateinit var weatherDetailTemperatureMaxTextView : TextView
    private lateinit var weatherDetailTemperatureMinTextView : TextView

    private var positionItemList : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast_details)
        positionItemList = intent.getIntExtra(WeatherForecastListFragment.WEATHER_FORECAST_DETAILS_POSITION_CONST,0)
        init()
        loadData(positionItemList)


    }

    fun loadData(position : Int){
        DataManager.getWeatherForecastDayDetail(object : IApiResponse<WeatherForecastDay>
        {
            override fun onSuccess(obj: WeatherForecastDay?) {
                setForecastDetailDayData(obj)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
                ,position)
    }

    fun init(){
        weatherDetailDayTextView = findViewById(R.id.weather_forecast_day_detail_day_textView)
        weatherDetailCityTextView = findViewById(R.id.weather_forecast_day_detail_city_textView)

        weatherDetailTemperatureNightTextView = findViewById(R.id.weather_forecast_day_detail_temperature_night_textView)
        weatherDetailTemperatureMorningTextView = findViewById(R.id.weather_forecast_day_detail_temperature_morning_textView)
        weatherDetailTemperatureMaxTextView = findViewById(R.id.weather_forecast_day_detail_temperature_max_textView)
        weatherDetailTemperatureMinTextView = findViewById(R.id.weather_forecast_day_detail_temperature_min_textView)
    }

    fun setForecastDetailDayData(weatherForecastDay : WeatherForecastDay?){
        weatherDetailTemperatureNightTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.night)?.toInt().toString() + getString(R.string.weather_activity_unit_degree)
        weatherDetailTemperatureMorningTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.morn)?.toInt().toString() + getString(R.string.weather_activity_unit_degree)
        weatherDetailTemperatureMaxTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.max)?.toInt().toString() + getString(R.string.weather_activity_unit_degree)
        weatherDetailTemperatureMinTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.min)?.toInt().toString() + getString(R.string.weather_activity_unit_degree)
    }
}
