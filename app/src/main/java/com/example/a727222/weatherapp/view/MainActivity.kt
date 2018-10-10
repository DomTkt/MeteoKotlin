package com.example.a727222.weatherapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.adapter.WeatherForecastAdapter
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.manager.DataManager
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnItemWeatherForecastClickListener {

    private lateinit var textViewWeatherCity : TextView
    private lateinit var textViewWeatherMain : TextView
    private lateinit var textViewWeatherTemperature : TextView
    private lateinit var imageViewWeatherIcon : ImageView

    private lateinit var textViewWeatherSunrise : TextView
    private lateinit var textViewWeatherSunset : TextView
    private lateinit var textViewWeatherClouds : TextView
    private lateinit var textViewWeatherRain : TextView
    private lateinit var textViewWeatherHumidity : TextView
    private lateinit var textViewWeatherPressure : TextView
    private lateinit var recyclerViewWeatherForecast : RecyclerView


    private var forecastItemList : ArrayList<ForecastItem> = ArrayList<ForecastItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        loadData()
    }

    fun setCurrentWeatherData(weatherCurrent: WeatherCurrent?){
        textViewWeatherCity.setText(weatherCurrent?.name)
        textViewWeatherMain.setText(weatherCurrent?.weather?.get(0)?.main)
        textViewWeatherTemperature.setText(Utils.convertKelvinToCelsius(weatherCurrent?.main?.temp)?.toInt().toString() + getString(R.string.weather_activity_unit_degree))

        textViewWeatherSunrise.setText(getString(R.string.weather_activity_label_sunrise) + weatherCurrent?.sys?.sunrise.toString())
        textViewWeatherSunset.setText(getString(R.string.weather_activity_label_sunset) + weatherCurrent?.sys?.sunset.toString())
        textViewWeatherClouds.setText(getString(R.string.weather_activity_label_clouds) + weatherCurrent?.clouds?.all.toString() + getString(R.string.weather_activity_unit_percent))
        if(weatherCurrent?.rain == null){
            textViewWeatherRain.setText(getString(R.string.weather_activity_label_rain) + getString(R.string.weather_activity_rain_empty_state))
        }else {
            textViewWeatherRain.setText(getString(R.string.weather_activity_label_rain) + weatherCurrent.rain.volume.toString() + getString(R.string.weather_activity_unit_volume))
        }
        textViewWeatherHumidity.setText(getString(R.string.weather_activity_label_humidity) + weatherCurrent?.main?.humidity.toString() + getString(R.string.weather_activity_unit_percent))
        textViewWeatherPressure.setText(getString(R.string.weather_activity_label_pressure) + weatherCurrent?.main?.pressure.toString() + getString(R.string.weather_activity_unit_pressure))
    }

    fun init(){
        textViewWeatherCity = findViewById(R.id.weather_activity_city_textView)
        textViewWeatherMain = findViewById(R.id.weather_activity_main_textView)
        textViewWeatherTemperature = findViewById(R.id.weather_activity_temperature_textView)

        textViewWeatherSunrise = findViewById(R.id.weather_activity_sunrise_textView)
        textViewWeatherSunset = findViewById(R.id.weather_activity_sunset_textView)
        textViewWeatherClouds = findViewById(R.id.weather_activity_cloud_textView)
        textViewWeatherRain = findViewById(R.id.weather_activity_rain_textView)
        textViewWeatherHumidity = findViewById(R.id.weather_activity_humidity_textView)
        textViewWeatherPressure = findViewById(R.id.weather_activity_pressure_textView)
        recyclerViewWeatherForecast = findViewById(R.id.weather_activity_forecast_recyclerView)
    }

    fun loadData(){
        DataManager.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                    setCurrentWeatherData(obj)
                    DataManager.getWeatherForecastForOneWeek(object : IApiResponse<WeatherForecast>
                    {
                        override fun onSuccess(obj: WeatherForecast?) {

                                setForecastItem(obj)
                                recyclerViewWeatherForecast.layoutManager = LinearLayoutManager(this@MainActivity)
                                recyclerViewWeatherForecast.adapter = WeatherForecastAdapter(forecastItemList,this@MainActivity,this@MainActivity)

                        }

                        override fun onError(t: Throwable) {
                            println(t)
                        }

                    }
                    )



            }

            override fun onError(t: Throwable) {
                println(t)
            }

        }
        )
    }

    fun setForecastItem(weatherForecast : WeatherForecast?){


        val SimpleDateFormatDayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH)


        for((index,forecastItem) in weatherForecast?.list?.withIndex()!!){
            val calendar : Calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, index)
            val forecastDate : Date = calendar.time
            val forecastItem = ForecastItem(day = SimpleDateFormatDayOfWeek.format(forecastDate), temperature_min = Utils.convertKelvinToCelsius(forecastItem.temp.min)?.toInt(), temperature_max = Utils.convertKelvinToCelsius(forecastItem.temp.max)?.toInt())
            forecastItemList.add(forecastItem)

        }


    }


}
