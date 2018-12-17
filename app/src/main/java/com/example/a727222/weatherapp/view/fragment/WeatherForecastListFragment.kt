package com.example.a727222.weatherapp.view.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.adapter.WeatherForecastAdapter
import com.example.a727222.weatherapp.component.DaggerComponentWeatherForecastList
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import com.example.a727222.weatherapp.view.activity.WeatherForecastDetailsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_weather_forecast_list.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * This class is a fragment which contains weather forecast data
 */
class WeatherForecastListFragment : Fragment(), OnItemWeatherForecastClickListener {

    companion object {

        fun newInstance(): WeatherForecastListFragment {
            return WeatherForecastListFragment()
        }

        val WEATHER_FORECAST_DAY_EXTRA : String = "WEATHER_FORECAST_DAY_EXTRA"
    }

    /**
     * event click listener for the position of the weather forecast list clicked
     */
    override fun onItemWeatherClick(position: Int) {
        val intent = Intent(this.context, WeatherForecastDetailsActivity::class.java)
        val weatherForecastDay : WeatherForecastDay? = weatherForecast?.list?.get(position)
        weatherForecastDay?.city = weatherForecast?.city
        weatherForecastDay?.day = forecastItemList.get(position).day
        intent.putExtra(WEATHER_FORECAST_DAY_EXTRA,weatherForecastDay)
        startActivity(intent)

    }

    /**
     * forecastItemList ArrayList of ForecastItem
     */
    private var forecastItemList : ArrayList<ForecastItem> = ArrayList<ForecastItem>()
    /**
     * weatherForecast allows to temporarily stored the weatherForecast to change its content
     */
    private var weatherForecast : WeatherForecast? = null
    /**
     * citySearch contains the String of the city search
     */
    var citySearch : String? = null

    /**
     * presenter contains the instance of IA
     */
    @Inject
    lateinit var presenter : IA

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_weather_forecast_list, container, false)
        val b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        DaggerComponentWeatherForecastList.builder().moduleWeatherForecastList(ModuleWeatherForecastList(requireContext())).build().plus(this)

        presenter.updateWeatherForecastListSearch(citySearch)
        presenter.weatherForecastListData
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe( { weatherForecast ->
                    setForecastItem(weatherForecast)
                }, { throwable -> Toast.makeText(requireContext(),"Erreur = " + throwable.message, Toast.LENGTH_SHORT).show()

                })
        return view
    }

    /**
     * set the data to the view
     */
    private fun setForecastItem(weatherForecast: WeatherForecast?) {
        this.weatherForecast = weatherForecast
        weather_forecast_list_fragment_recyclerView.layoutManager = LinearLayoutManager(this@WeatherForecastListFragment.context)
        weather_forecast_list_fragment_recyclerView.adapter = WeatherForecastAdapter(forecastItemList,this@WeatherForecastListFragment)

        val simpleDateFormatDayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH)

        weatherForecast?.list?.let {
            for((index,forecastItems) in it.withIndex()){
                val calendar : Calendar = Calendar.getInstance()
                calendar.add(Calendar.DAY_OF_YEAR, index)
                val forecastDate : Date = calendar.time
                val forecastItem = ForecastItem(day = simpleDateFormatDayOfWeek.format(forecastDate), temperature_min = Utils.convertKelvinToCelsius(forecastItems.temp.min), temperature_max = Utils.convertKelvinToCelsius(forecastItems.temp.max),icon = forecastItems.weather.get(0).icon)
                forecastItemList.add(forecastItem)

            }
        }
    }
}
