package com.example.a727222.weatherapp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.component.DaggerComponentWeatherCurrentPrincipalDetails
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleWeatherCurrentPrincipalDetails
import com.example.a727222.weatherapp.presenter.WeatherCurrentPrincipalDetailsFragmentPresenter
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import javax.inject.Inject

class WeatherCurrentPrincipalDetailsFragment : Fragment(){

    private lateinit var textViewWeatherCity : TextView
    private lateinit var textViewWeatherMain : TextView
    private lateinit var textViewWeatherTemperature : TextView
    private lateinit var imageViewWeatherIcon : ImageView

    @Inject
    lateinit var presenter  : WeatherCurrentPrincipalDetailsFragmentPresenter
    
    var citySearch : String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_weather_current_principal_details, container, false)
        init(view)
        var b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        DaggerComponentWeatherCurrentPrincipalDetails.builder().moduleWeatherCurrentPrincipalDetails(ModuleWeatherCurrentPrincipalDetails(requireContext())).build().plus(this)

            presenter.updateWeatherCurrentPrincipalDetailsDataSearch(citySearch)
        presenter.weatherCurrentPrincipalDetailsData
                .subscribe( { weatherCurrent ->
                    setWeatherCurrentPrincipalDetailsData(weatherCurrent)
                },
                        { throwable -> println(throwable.message)

                })

        return view
    }

    companion object {
        fun newInstance(): WeatherCurrentPrincipalDetailsFragment {
            return WeatherCurrentPrincipalDetailsFragment()
        }
    }

    fun init(view : View){
        imageViewWeatherIcon = view.findViewById(R.id.weather_current_principal_details_icon_imageView)
        textViewWeatherCity = view.findViewById(R.id.weather_current_principal_details_city_textView)
        textViewWeatherMain = view.findViewById(R.id.weather_current_principal_details_main_textView)
        textViewWeatherTemperature = view.findViewById(R.id.weather_current_principal_details_temperature_textView)
    }

     fun setWeatherCurrentPrincipalDetailsData(weatherCurrent: WeatherCurrent?) {
        imageViewWeatherIcon.setImageResource(Utils.getTypeIconId(weatherCurrent?.weather?.get(0)?.icon))
        textViewWeatherCity.setText(weatherCurrent?.name)
        textViewWeatherMain.setText(weatherCurrent?.weather?.get(0)?.main)
        textViewWeatherTemperature.setText(Utils.convertKelvinToCelsius(weatherCurrent?.main?.temp)?.toString() + getString(R.string.weather_activity_unit_degree))
    }
}
