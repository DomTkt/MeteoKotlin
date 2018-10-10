package com.example.a727222.weatherapp.utils

object Utils {

    fun convertKelvinToCelsius(tempKelvin: Double?): Double? {
        var tempCelsius : Double? = tempKelvin?.minus(273.15)
        return tempCelsius
    }

}