package com.example.a727222.weatherapp.presenter

import com.example.a727222.weatherapp.models.*
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherForecastListFragmentPresenterTest {

    lateinit var weatherForecast : WeatherForecast
    lateinit var weatherForecastTrunc : WeatherForecast

    @Before
    fun init(){

        var coord = Coord(-27.7127,-67.1356)
        var city = City(3846616,name = "Londres",country = "AR", coord = coord)
        var cod = "200"
        var weatherForecastDay = WeatherForecastDay(cloudss = 0,dt = 1541952000,temp = Temp(283.28,283.28,283.28,283.28,283.28,morn = 283.28),pressure = 790.27,day = "",city = null,deg = 27,humidity = 82,
                rain = 0.0,speed = 0.17,snow = 0.0,weather = emptyList())
        var list : List<WeatherForecastDay> = ArrayList<WeatherForecastDay>()
        list.plus(weatherForecastDay)
        list.plus(weatherForecastDay)
        list.plus(weatherForecastDay)
        list.plus(weatherForecastDay)
        list.plus(weatherForecastDay)
        list.plus(weatherForecastDay)
        list.plus(weatherForecastDay)

        var listTrunc : List<WeatherForecastDay> = ArrayList<WeatherForecastDay>()
        listTrunc.plus(weatherForecastDay)
        listTrunc.plus(weatherForecastDay)
        listTrunc.plus(weatherForecastDay)
        weatherForecast = WeatherForecast(city,cod,list = list,cnt = 7, message = null)
        weatherForecastTrunc = WeatherForecast(city,cod,list = listTrunc,cnt = 7, message = null)
    }

    @Test
    fun truncListWeather() {

        var actual : WeatherForecast = weatherForecast
        actual?.list = actual.list?.take(3)
        var expected : WeatherForecast? = weatherForecastTrunc
        assertEquals("Trunc obj WeatherForecast",actual,expected)

        //TODO Tester tout les cas

    }
}