package com.example.a727222.weatherapp.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.presenter.WeatherForecastDetailsActivityPresenter
import com.example.a727222.weatherapp.utils.Utils
import kotlinx.android.synthetic.main.activity_weather_forecast_details.*

/**
 * This class contains datas for a weather forecast day
 */
class WeatherForecastDetailsActivity : AppCompatActivity(), WeatherForecastDetailsActivityPresenter.View {

    private lateinit var presenter: WeatherForecastDetailsActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast_details)
        presenter = WeatherForecastDetailsActivityPresenter(intent,this)
        presenter.updateForecastDetailDay()
    }

    override fun setForecastDetailDayData(weatherForecastDay: WeatherForecastDay?) {
        weather_forecast_day_detail_day_textView.text = weatherForecastDay?.day
        weather_forecast_day_detail_city_textView.text = weatherForecastDay?.city?.name
        weather_forecast_day_detail_temperature_textView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.day)?.toString()
        weather_forecast_day_detail_icon_imageView.setImageResource(Utils.getTypeIconId(weatherForecastDay?.weather?.get(0)?.icon))

        weather_forecast_day_detail_temperature_night_textView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.night)?.toString() + getString(R.string.weather_activity_unit_degree)
        weather_forecast_day_detail_temperature_morning_textView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.morn)?.toString() + getString(R.string.weather_activity_unit_degree)
        weather_forecast_day_detail_temperature_max_textView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.max)?.toString() + getString(R.string.weather_activity_unit_degree)
        weather_forecast_day_detail_temperature_min_textView.text = Utils.convertKelvinToCelsius(weatherForecastDay?.temp?.min)?.toString() + getString(R.string.weather_activity_unit_degree)
    }


}
