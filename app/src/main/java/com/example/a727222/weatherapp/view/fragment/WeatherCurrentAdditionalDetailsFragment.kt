package com.example.a727222.weatherapp.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.component.DaggerComponentWeatherCurrentAdditionalDetails
import com.example.a727222.weatherapp.models.WeatherCurrent
import com.example.a727222.weatherapp.module.ModuleWeatherCurrentAdditionalDetails
import com.example.a727222.weatherapp.presenter.WeatherCurrentAdditionalDetailsFragmentPresenter
import com.example.a727222.weatherapp.utils.Utils
import com.example.a727222.weatherapp.view.activity.WeatherActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_weather_current_additional_details.*
import javax.inject.Inject

/**
 * This class is a fragment which contains weather current additional details data
 */
class WeatherCurrentAdditionalDetailsFragment : Fragment() {

    /**
     * presenter contains instance of WeatherCurrentAdditionalDetailsFragmentPresenter
     */
    @Inject
    lateinit var presenter : WeatherCurrentAdditionalDetailsFragmentPresenter

    /**
     * citySearch contains the String of the city search
     */
    private var citySearch : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_weather_current_additional_details, container, false)
        var b : Bundle? = arguments
        citySearch = b?.getString(WeatherActivity.WEATHER_ACTIVITY_ARGUMENTS)
        DaggerComponentWeatherCurrentAdditionalDetails.builder().moduleWeatherCurrentAdditionalDetails(ModuleWeatherCurrentAdditionalDetails(requireContext())).build().plus(this)

            presenter.updateWeatherCurrentAdditionalDetailsDataSearch(citySearch)
            presenter.weatherCurrentAdditionalDetailsData
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe( { weatherCurrent ->
                        setWeatherCurrentAdditionalDetailsData(weatherCurrent)
                },
                        { throwable -> println(throwable.message)
                })
        return view
    }

    companion object {

        fun newInstance(): WeatherCurrentAdditionalDetailsFragment {
            return WeatherCurrentAdditionalDetailsFragment()
        }
    }

    /**
     * set data weatherCurrent in the view
     */
    private fun setWeatherCurrentAdditionalDetailsData(weatherCurrent: WeatherCurrent?) {
        weather_current_additional_details_sunrise_textView.setText(getString(R.string.weather_activity_label_sunrise) + Utils.convertTimeStampInSuntimes(weatherCurrent?.sys?.sunrise?.toLong()))
        weather_current_additional_details_sunset_textView.setText(getString(R.string.weather_activity_label_sunset) + Utils.convertTimeStampInSuntimes(weatherCurrent?.sys?.sunset?.toLong()))
        weather_current_additional_details_cloud_textView.setText(getString(R.string.weather_activity_label_clouds) + weatherCurrent?.clouds?.all.toString() + getString(R.string.weather_activity_unit_percent))
        if(weatherCurrent?.rain == null){
            weather_current_additional_details_rain_textView.setText(getString(R.string.weather_activity_label_rain) + getString(R.string.weather_activity_rain_empty_state))
        }else {
            weather_current_additional_details_rain_textView.setText(getString(R.string.weather_activity_label_rain) + weatherCurrent.rain?.volume.toString() + getString(R.string.weather_activity_unit_volume))
        }
        weather_current_additional_details_humidity_textView.setText(getString(R.string.weather_activity_label_humidity) + weatherCurrent?.main?.humidity.toString() + getString(R.string.weather_activity_unit_percent))
        weather_current_additional_details_pressure_textView.setText(getString(R.string.weather_activity_label_pressure) + weatherCurrent?.main?.pressure.toString() + getString(R.string.weather_activity_unit_pressure))
    }
}
