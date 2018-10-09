package com.example.a727222.weatherapp.utils

object Utils {

    fun convertKelvinToCelsius(tempKelvin : Double): Double {
        var tempCelsius : Double = tempKelvin - 273.15
        return tempCelsius
    }

}