package com.example.a727222.weatherapp.utils

import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.constant.WeatherConstants
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun convertKelvinToCelsius(tempKelvin: Double?): Int? {
        var tempCelsius : Double? = tempKelvin?.minus(273.15)
        return tempCelsius?.toInt()
    }

    fun convertTimeStampInSuntimes(milliseconds : Long?) : String{
        var simpleDateFormat : SimpleDateFormat = SimpleDateFormat("kk:mm")
        var date : Date? = milliseconds?.times(1000)?.let { Date(it) }
        var stringSunTime : String =  simpleDateFormat.format(date)
        return stringSunTime
    }

    fun getTypeIconId (type : String?) : Int{

        var id = when(type){
            WeatherConstants.CLEAR_SKY_DAY -> R.drawable.clear_sky
            WeatherConstants.CLEAR_SKY_NIGHT -> R.drawable.night

            WeatherConstants.FEW_CLOUD_DAY -> R.drawable.few_cloud
            WeatherConstants.FEW_CLOUD_NIGHT -> R.drawable.night_cloud

            WeatherConstants.SCATTERED_CLOUDS_DAY -> R.drawable.cloud
            WeatherConstants.SCATTERED_CLOUDS_NIGHT -> R.drawable.cloud

            WeatherConstants.BROKEN_CLOUDS_DAY -> R.drawable.cloud
            WeatherConstants.BROKEN_CLOUDS_NIGHT -> R.drawable.cloud

            WeatherConstants.SHOWER_RAIN_DAY -> R.drawable.rain_shower
            WeatherConstants.SHOWER_RAIN_NIGHT -> R.drawable.rain_shower

            WeatherConstants.RAIN_DAY -> R.drawable.rain_clear
            WeatherConstants.RAIN_NIGHT -> R.drawable.rain_clear

            WeatherConstants.THUNDERSTORM_DAY -> R.drawable.thunder
            WeatherConstants.THENDERSTORM_NIGHT -> R.drawable.thunder

            WeatherConstants.SNOW_DAY -> R.drawable.snow
            WeatherConstants.SNOW_NIGHT -> R.drawable.snow

            WeatherConstants.MIST_DAY -> R.drawable.mist
            WeatherConstants.MIST_NIGHT -> R.drawable.mist

            else -> -1
        }
        return id
    }
}