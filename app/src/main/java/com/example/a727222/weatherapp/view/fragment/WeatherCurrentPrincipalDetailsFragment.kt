package com.example.a727222.weatherapp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.component.DaggerComponentWeatherCurrentPrincipalDetails
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleWeatherCurrentPrincipalDetails
import com.example.a727222.weatherapp.presenter.WeatherCurrentPrincipalDetailsFragmentPresenter
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import kotlinx.android.synthetic.main.fragment_weather_current_principal_details.*
import javax.inject.Inject

class WeatherCurrentPrincipalDetailsFragment : Fragment(){

    @Inject
    lateinit var presenter  : WeatherCurrentPrincipalDetailsFragmentPresenter
    
    private var citySearch : String? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_weather_current_principal_details, container, false)
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

     private fun setWeatherCurrentPrincipalDetailsData(weatherCurrent: WeatherCurrent?) {
         weather_current_principal_details_icon_imageView.setImageResource(Utils.getTypeIconId(weatherCurrent?.weather?.get(0)?.icon))
         weather_current_principal_details_city_textView.setText(weatherCurrent?.name)
         weather_current_principal_details_main_textView.setText(weatherCurrent?.weather?.get(0)?.main)
         weather_current_principal_details_temperature_textView.setText(Utils.convertKelvinToCelsius(weatherCurrent?.main?.temp)?.toString() + getString(R.string.weather_activity_unit_degree))
    }
}
