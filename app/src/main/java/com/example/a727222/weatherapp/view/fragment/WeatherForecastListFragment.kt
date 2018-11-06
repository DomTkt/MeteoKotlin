package com.example.a727222.weatherapp.view.fragment


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
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.presenter.WeatherForecastListFragmentPresenter
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import com.example.a727222.weatherapp.view.activity.WeatherForecastDetailsActivity
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class WeatherForecastListFragment : Fragment(), OnItemWeatherForecastClickListener, WeatherForecastListFragmentPresenter.View {

    companion object {

        fun newInstance(): WeatherForecastListFragment {
            return WeatherForecastListFragment()
        }

        val WEATHER_FORECAST_DAY_EXTRA : String = "WEATHER_FORECAST_DAY_EXTRA"
    }

    override fun onItemWeatherClick(position: Int) {
        val intent = Intent(this.context, WeatherForecastDetailsActivity::class.java)
        var weatherForecastDay : WeatherForecastDay? = weatherForecast?.list?.get(position)
        weatherForecastDay?.city = weatherForecast?.city
        weatherForecastDay?.day = forecastItemList.get(position).day
        intent.putExtra(WEATHER_FORECAST_DAY_EXTRA,weatherForecastDay)
        startActivity(intent)

    }

    private var forecastItemList : ArrayList<ForecastItem> = ArrayList<ForecastItem>()
    private var weatherForecast : WeatherForecast? = null
    private lateinit var recyclerViewWeatherForecast : RecyclerView
    var citySearch : String? = null
    private lateinit var presenter : WeatherForecastListFragmentPresenter

    @Inject
    lateinit var networking : Networking

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_weather_forecast_list, container, false)
        recyclerViewWeatherForecast = view.findViewById(R.id.weather_forecast_list_fragment_recyclerView)
        var b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        presenter = WeatherForecastListFragmentPresenter(requireContext(),citySearch,this)
        if(citySearch != null){
            presenter.updateWeatherForecastListSearch()
        }else {
            presenter.updateWeatherForecastList()
        }
        return view
    }

    override fun setForecastItem(weatherForecast: WeatherForecast?) {
        this.weatherForecast = weatherForecast

        recyclerViewWeatherForecast.layoutManager = LinearLayoutManager(this@WeatherForecastListFragment.context)
        recyclerViewWeatherForecast.adapter = WeatherForecastAdapter(forecastItemList,this@WeatherForecastListFragment.requireContext(),this@WeatherForecastListFragment)

        val SimpleDateFormatDayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH)
        for((index,forecastItems) in weatherForecast?.list?.withIndex()!!){
            val calendar : Calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, index)
            val forecastDate : Date = calendar.time
            val forecastItem = ForecastItem(day = SimpleDateFormatDayOfWeek.format(forecastDate), temperature_min = Utils.convertKelvinToCelsius(forecastItems.temp.min), temperature_max = Utils.convertKelvinToCelsius(forecastItems.temp.max),icon = forecastItems.weather.get(0).icon)
            forecastItemList.add(forecastItem)

        }
    }
}
