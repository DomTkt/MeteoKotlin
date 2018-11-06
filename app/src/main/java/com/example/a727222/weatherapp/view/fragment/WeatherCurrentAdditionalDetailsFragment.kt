package com.example.a727222.weatherapp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import javax.inject.Inject

class WeatherCurrentAdditionalDetailsFragment : Fragment(), WeatherCurrentAdditionalDetailsFragmentPresenter.View {

    private lateinit var textViewWeatherSunrise : TextView
    private lateinit var textViewWeatherSunset : TextView
    private lateinit var textViewWeatherClouds : TextView
    private lateinit var textViewWeatherRain : TextView
    private lateinit var textViewWeatherHumidity : TextView
    private lateinit var textViewWeatherPressure : TextView
    private lateinit var presenter : WeatherCurrentAdditionalDetailsFragmentPresenter

    var citySearch : String? = null

    @Inject
    lateinit var networking : Networking

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_weather_current_additional_details, container, false)
        init(view)
        var b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        presenter = WeatherCurrentAdditionalDetailsFragmentPresenter(requireContext(),citySearch,this)
        if(citySearch != null){
            presenter.updateWeatherCurrentAdditionalDetailsDataSearch()
        }else {
            presenter.updateWeatherCurrentAdditionalDetailsData()
        }
        return view
    }

    companion object {

        fun newInstance(): WeatherCurrentAdditionalDetailsFragment {
            return WeatherCurrentAdditionalDetailsFragment()
        }
    }

    fun init(view : View){
        textViewWeatherSunrise = view.findViewById(R.id.weather_current_additional_details_sunrise_textView)
        textViewWeatherSunset = view.findViewById(R.id.weather_current_additional_details_sunset_textView)
        textViewWeatherClouds = view.findViewById(R.id.weather_current_additional_details_cloud_textView)
        textViewWeatherRain = view.findViewById(R.id.weather_current_additional_details_rain_textView)
        textViewWeatherHumidity = view.findViewById(R.id.weather_current_additional_details_humidity_textView)
        textViewWeatherPressure = view.findViewById(R.id.weather_current_additional_details_pressure_textView)
    }

    override fun setWeatherCurrentAdditionalDetailsData(weatherCurrent: WeatherCurrent?) {
        textViewWeatherSunrise.setText(getString(R.string.weather_activity_label_sunrise) + Utils.convertTimeStampInSuntimes(weatherCurrent?.sys?.sunrise?.toLong()))
        textViewWeatherSunset.setText(getString(R.string.weather_activity_label_sunset) + Utils.convertTimeStampInSuntimes(weatherCurrent?.sys?.sunset?.toLong()))
        textViewWeatherClouds.setText(getString(R.string.weather_activity_label_clouds) + weatherCurrent?.clouds?.all.toString() + getString(R.string.weather_activity_unit_percent))
        if(weatherCurrent?.rain == null){
            textViewWeatherRain.setText(getString(R.string.weather_activity_label_rain) + getString(R.string.weather_activity_rain_empty_state))
        }else {
            textViewWeatherRain.setText(getString(R.string.weather_activity_label_rain) + weatherCurrent.rain?.volume.toString() + getString(R.string.weather_activity_unit_volume))
        }
        textViewWeatherHumidity.setText(getString(R.string.weather_activity_label_humidity) + weatherCurrent?.main?.humidity.toString() + getString(R.string.weather_activity_unit_percent))
        textViewWeatherPressure.setText(getString(R.string.weather_activity_label_pressure) + weatherCurrent?.main?.pressure.toString() + getString(R.string.weather_activity_unit_pressure))
    }
}
