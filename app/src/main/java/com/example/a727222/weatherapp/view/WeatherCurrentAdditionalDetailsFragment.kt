package com.example.a727222.weatherapp.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.RestClientK
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.manager.DataManager
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.utils.Utils

class WeatherCurrentAdditionalDetailsFragment : Fragment() {

    private lateinit var textViewWeatherSunrise : TextView
    private lateinit var textViewWeatherSunset : TextView
    private lateinit var textViewWeatherClouds : TextView
    private lateinit var textViewWeatherRain : TextView
    private lateinit var textViewWeatherHumidity : TextView
    private lateinit var textViewWeatherPressure : TextView

    var citySearch : String? = null

    lateinit var manager : DataManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var restClientK : RestClientK = RestClientK()
        manager = DataManager(this.requireContext(),networker = restClientK)

//        var mockClient : MockClient = MockClient(this.requireContext())
//        manager = DataManager(this.requireContext(),mockClient)

        val view = inflater?.inflate(R.layout.fragment_weather_current_additional_details, container, false)
        init(view)
        var b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        if(citySearch != null){
            loadDataSearch(citySearch)
        }else {
            loadData()
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

    fun setWeatherCurrentAdditionalDetailsData(weatherCurrent: WeatherCurrent?){

        textViewWeatherSunrise.setText(getString(R.string.weather_activity_label_sunrise) + Utils.convertTimeStampInSuntimes(weatherCurrent?.sys?.sunrise?.toLong()))
        textViewWeatherSunset.setText(getString(R.string.weather_activity_label_sunset) + Utils.convertTimeStampInSuntimes(weatherCurrent?.sys?.sunset?.toLong()))
        textViewWeatherClouds.setText(getString(R.string.weather_activity_label_clouds) + weatherCurrent?.clouds?.all.toString() + getString(R.string.weather_activity_unit_percent))
        if(weatherCurrent?.rain == null){
            textViewWeatherRain.setText(getString(R.string.weather_activity_label_rain) + getString(R.string.weather_activity_rain_empty_state))
        }else {
            textViewWeatherRain.setText(getString(R.string.weather_activity_label_rain) + weatherCurrent.rain.volume.toString() + getString(R.string.weather_activity_unit_volume))
        }
        textViewWeatherHumidity.setText(getString(R.string.weather_activity_label_humidity) + weatherCurrent?.main?.humidity.toString() + getString(R.string.weather_activity_unit_percent))
        textViewWeatherPressure.setText(getString(R.string.weather_activity_label_pressure) + weatherCurrent?.main?.pressure.toString() + getString(R.string.weather_activity_unit_pressure))
    }

    fun loadData(){
        manager.getCurrentWeather(object : IApiResponse<WeatherCurrent>
        {
            override fun onSuccess(obj: WeatherCurrent?) {
                setWeatherCurrentAdditionalDetailsData(obj)
            }

            override fun onError(t: Throwable) {
                println(t)
            }
        }
        )
    }

    fun loadDataSearch(searchCity : String?){
        manager.getCurrentWeatherSearch(object : IApiResponse<WeatherCurrent>{
            override fun onSuccess(obj: WeatherCurrent?) {
                if(obj != null) {
                    setWeatherCurrentAdditionalDetailsData(obj)
                }else{
                    loadData()
                }
            }

            override fun onError(t: Throwable) {

            }

        },searchCity)
    }
}
