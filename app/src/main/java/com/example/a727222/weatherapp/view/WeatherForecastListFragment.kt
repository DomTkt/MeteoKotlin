package com.example.a727222.weatherapp.view


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.adapter.WeatherForecastAdapter
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.manager.DataManager
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.*



class WeatherForecastListFragment : Fragment(), OnItemWeatherForecastClickListener {


    companion object {

        fun newInstance(): WeatherForecastListFragment {
            return WeatherForecastListFragment()
        }

        val WEATHER_FORECAST_DETAILS_POSITION_CONST : String = "WeatherForecastDetailsPosition"
    }

    override fun onItemWeatherClick(position: Int) {

        val intent = Intent(this.context,WeatherForecastDetailsActivity::class.java)
        intent.putExtra(WEATHER_FORECAST_DETAILS_POSITION_CONST,position)
        startActivity(intent)

    }

    private var forecastItemList : ArrayList<ForecastItem> = ArrayList<ForecastItem>()
    private var weatherForecast : WeatherForecast? = null
    private lateinit var recyclerViewWeatherForecast : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_weather_forecast_list, container, false)
        recyclerViewWeatherForecast = view.findViewById(R.id.weather_forecast_list_fragment_recyclerView)
        loadData()
        return view
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

    fun loadData(){
        DataManager.getWeatherForecastForOneWeek(object : IApiResponse<WeatherForecast>
        {
            override fun onSuccess(obj: WeatherForecast?) {
                weatherForecast = obj
                setForecastItem(obj)
                recyclerViewWeatherForecast.layoutManager = LinearLayoutManager(this@WeatherForecastListFragment.context)
                recyclerViewWeatherForecast.adapter = WeatherForecastAdapter(forecastItemList,this@WeatherForecastListFragment.requireContext(),this@WeatherForecastListFragment)

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        }
        )
    }

}