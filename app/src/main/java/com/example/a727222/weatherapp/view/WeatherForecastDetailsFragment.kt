package com.example.a727222.weatherapp.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a727222.weatherapp.R

class WeatherForecastDetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_weather_forecast_details, container, false)
        }


    companion object {

        fun newInstance(): WeatherForecastDetailsFragment {
            return WeatherForecastDetailsFragment()
        }
    }

}

