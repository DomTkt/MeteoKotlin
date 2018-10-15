package com.example.a727222.weatherapp.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.manager.DataManager
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.utils.Utils

class WeatherCurrentPrincipalDetailsFragment : Fragment() {

    private lateinit var textViewWeatherCity : TextView
    private lateinit var textViewWeatherMain : TextView
    private lateinit var textViewWeatherTemperature : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_weather_current_principal_details, container, false)
        init(view)
        loadData()
        return view
    }

    companion object {
        fun newInstance(): WeatherCurrentPrincipalDetailsFragment {
            return WeatherCurrentPrincipalDetailsFragment()
        }
    }

    fun init(view : View){
        textViewWeatherCity = view.findViewById(R.id.weather_current_principal_details_city_textView)
        textViewWeatherMain = view.findViewById(R.id.weather_current_principal_details_main_textView)
        textViewWeatherTemperature = view.findViewById(R.id.weather_current_principal_details_temperature_textView)
    }

    fun setWeatherCurrentPrincipalDetailsData(weatherCurrent: WeatherCurrent?){
        textViewWeatherCity.setText(weatherCurrent?.name)
        textViewWeatherMain.setText(weatherCurrent?.weather?.get(0)?.main)
        textViewWeatherTemperature.setText(Utils.convertKelvinToCelsius(weatherCurrent?.main?.temp)?.toInt().toString() + getString(R.string.weather_activity_unit_degree))

    }

    fun loadData(){
        DataManager.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                setWeatherCurrentPrincipalDetailsData(obj)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
        )
    }

}
