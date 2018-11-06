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
import com.example.a727222.weatherapp.daggerTestDeleteAfter.DaggerMyComponent
import com.example.a727222.weatherapp.daggerTestDeleteAfter.MyModule
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.models.WeatherForecast
import com.example.a727222.weatherapp.models.WeatherForecastDay
import com.example.a727222.weatherapp.utils.Utils
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class WeatherForecastListFragment : Fragment(), OnItemWeatherForecastClickListener {

    companion object {

        fun newInstance(): WeatherForecastListFragment {
            return WeatherForecastListFragment()
        }

        val WEATHER_FORECAST_DAY_EXTRA : String = "WEATHER_FORECAST_DAY_EXTRA"
    }

    override fun onItemWeatherClick(position: Int) {
        //TODO provide intent ... depuis composant dagger
        val intent = Intent(this.context,WeatherForecastDetailsActivity::class.java)
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
    @Inject
    lateinit var networking : Networking

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        DaggerMyComponent.builder().myModule(MyModule(requireContext())).build().plus(this)

        val view = inflater?.inflate(R.layout.fragment_weather_forecast_list, container, false)
        recyclerViewWeatherForecast = view.findViewById(R.id.weather_forecast_list_fragment_recyclerView)
        var b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        if(citySearch != null){
            loadDataSearch(citySearch)
        }else {
            loadData()
        }
        return view
    }

    fun setForecastItem(weatherForecast : WeatherForecast?){


        val SimpleDateFormatDayOfWeek = SimpleDateFormat("EEEE", Locale.ENGLISH)


        for((index,forecastItems) in weatherForecast?.list?.withIndex()!!){
            val calendar : Calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, index)
            val forecastDate : Date = calendar.time
            val forecastItem = ForecastItem(day = SimpleDateFormatDayOfWeek.format(forecastDate), temperature_min = Utils.convertKelvinToCelsius(forecastItems.temp.min), temperature_max = Utils.convertKelvinToCelsius(forecastItems.temp.max),icon = forecastItems.weather.get(0).icon)
            forecastItemList.add(forecastItem)

        }
    }

    fun loadData(){
        networking.getWeatherForecastForOneWeek(object : IApiResponse<WeatherForecast>
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

    fun loadDataSearch(city : String?){
        networking.getWeatherForecastForOneWeekSearch(object : IApiResponse<WeatherForecast>
        {
            override fun onSuccess(obj: WeatherForecast?) {

                if(obj != null) {
                    weatherForecast = obj
                    setForecastItem(obj)
                    recyclerViewWeatherForecast.layoutManager = LinearLayoutManager(this@WeatherForecastListFragment.context)
                    recyclerViewWeatherForecast.adapter = WeatherForecastAdapter(forecastItemList, this@WeatherForecastListFragment.requireContext(), this@WeatherForecastListFragment)
                }else{
                    loadData()
                }

            }

            override fun onError(t: Throwable) {
                println(t)
            }

        }
        ,searchCity = city)
    }
}
