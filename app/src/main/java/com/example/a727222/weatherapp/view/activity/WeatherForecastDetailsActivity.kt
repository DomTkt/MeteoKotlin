package com.example.a727222.weatherapp.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.presenter.WeatherForecastDetailsActivityPresenter
import com.example.a727222.weatherapp.utils.Utils

class WeatherForecastDetailsActivity : AppCompatActivity(), WeatherForecastDetailsActivityPresenter.View {

    private lateinit var weatherDetailDayTextView : TextView
    private lateinit var weatherDetailCityTextView : TextView
    private lateinit var weatherDetailTemperatureTextView : TextView
    private lateinit var weatherDetailIconImageView : ImageView

    private lateinit var weatherDetailTemperatureNightTextView : TextView
    private lateinit var weatherDetailTemperatureMorningTextView : TextView
    private lateinit var weatherDetailTemperatureMaxTextView : TextView
    private lateinit var weatherDetailTemperatureMinTextView : TextView

    private lateinit var presenter: WeatherForecastDetailsActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast_details)
        init()
        presenter = WeatherForecastDetailsActivityPresenter(intent,this)
        presenter.updateForecastDetailDay()
    }

    fun init(){
        weatherDetailDayTextView = findViewById(R.id.weather_forecast_day_detail_day_textView)
        weatherDetailCityTextView = findViewById(R.id.weather_forecast_day_detail_city_textView)
        weatherDetailTemperatureTextView = findViewById(R.id.weather_forecast_day_detail_temperature_textView)
        weatherDetailIconImageView = findViewById(R.id.weather_forecast_day_detail_icon_imageView)

        weatherDetailTemperatureNightTextView = findViewById(R.id.weather_forecast_day_detail_temperature_night_textView)
        weatherDetailTemperatureMorningTextView = findViewById(R.id.weather_forecast_day_detail_temperature_morning_textView)
        weatherDetailTemperatureMaxTextView = findViewById(R.id.weather_forecast_day_detail_temperature_max_textView)
        weatherDetailTemperatureMinTextView = findViewById(R.id.weather_forecast_day_detail_temperature_min_textView)
    }

    override fun setForecastDetailDayData(weatherForecastDay: WeatherForecastDay?) {
        weatherDetailDayTextView.text = weatherForecastDay?.day
        weatherDetailCityTextView.text = weatherForecastDay?.city?.name
        weatherDetailTemperatureTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.day)?.toString()
        weatherDetailIconImageView.setImageResource(Utils.getTypeIconId(weatherForecastDay?.weather?.get(0)?.icon))

        weatherDetailTemperatureNightTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.night)?.toString() + getString(R.string.weather_activity_unit_degree)
        weatherDetailTemperatureMorningTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.morn)?.toString() + getString(R.string.weather_activity_unit_degree)
        weatherDetailTemperatureMaxTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.max)?.toString() + getString(R.string.weather_activity_unit_degree)
        weatherDetailTemperatureMinTextView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.min)?.toString() + getString(R.string.weather_activity_unit_degree)
    }


}
