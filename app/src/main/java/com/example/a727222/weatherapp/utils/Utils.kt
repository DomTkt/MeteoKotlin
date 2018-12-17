package com.example.a727222.weatherapp.utils

import android.content.Context
import android.location.Geocoder
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.constant.WeatherConstants
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    /**
     * Take Kelvin in Double and convert on Celsius in Int
     */
    fun convertKelvinToCelsius(tempKelvin: Double?): Int? {
        var tempCelsius : Double? = tempKelvin?.minus(273.15)
        return tempCelsius?.toInt()
    }

    /**
     * @param milliseconds
     * convert TimeStamp in Suntimes
     */
    fun convertTimeStampInSuntimes(milliseconds : Long?) : String?{
        var simpleDateFormat : SimpleDateFormat = SimpleDateFormat("kk:mm")
        var date : Date? = milliseconds?.times(1000)?.let { Date(it) }
        var stringSunTime : String? =  simpleDateFormat.format(date)
        return stringSunTime
    }

    /**
     * @param type
     * get icon according to the type
     */
    fun getTypeIconId (type : String?) : Int{

        return when(type){

            WeatherConstants.CLEAR_SKY_DAY.value -> R.drawable.clear_sky
            WeatherConstants.CLEAR_SKY_NIGHT.value -> R.drawable.night

            WeatherConstants.FEW_CLOUD_DAY.value -> R.drawable.few_cloud
            WeatherConstants.FEW_CLOUD_NIGHT.value -> R.drawable.night_cloud

            WeatherConstants.SCATTERED_CLOUDS_DAY.value -> R.drawable.cloud
            WeatherConstants.SCATTERED_CLOUDS_NIGHT.value -> R.drawable.cloud

            WeatherConstants.BROKEN_CLOUDS_DAY.value -> R.drawable.cloud
            WeatherConstants.BROKEN_CLOUDS_NIGHT.value -> R.drawable.cloud

            WeatherConstants.SHOWER_RAIN_DAY.value -> R.drawable.rain_shower
            WeatherConstants.SHOWER_RAIN_NIGHT.value -> R.drawable.rain_shower

            WeatherConstants.RAIN_DAY.value -> R.drawable.rain_clear
            WeatherConstants.RAIN_NIGHT.value -> R.drawable.rain_clear

            WeatherConstants.THUNDERSTORM_DAY.value -> R.drawable.thunder
            WeatherConstants.THENDERSTORM_NIGHT.value -> R.drawable.thunder

            WeatherConstants.SNOW_DAY.value -> R.drawable.snow
            WeatherConstants.SNOW_NIGHT.value -> R.drawable.snow

            WeatherConstants.MIST_DAY.value -> R.drawable.mist
            WeatherConstants.MIST_NIGHT.value -> R.drawable.mist

            else -> -1
        }
    }


    /**
     * get the city from the location of the user
     * @param context
     * @param latitude latitude of the user
     * @param longitude longitude of the user
     */
    fun getCityNameFromLocation(context : Context, latitude : Double , longitude : Double) : String{

        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        var cityName = ""
        if(addresses != null) {
            cityName = addresses[0].locality
        }
        return cityName
    }


}