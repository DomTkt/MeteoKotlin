package com.example.a727222.weatherapp.presenter

import android.content.Context
import android.support.test.InstrumentationRegistry
import com.example.a727222.weatherapp.component.DaggerComponentWeatherForecastList
import com.example.a727222.weatherapp.interfaces.IA
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.interfaces.Networking
import com.example.a727222.weatherapp.models.*
import com.example.a727222.weatherapp.module.ModuleWeatherForecastList
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class WeatherForecastListFragmentPresenterAndroidTest : IA{

    override fun truncListWeather(weatherForecast: WeatherForecast?, truncLandscape: Int, truncPortrait: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var networking : Networking

    @Inject
    lateinit var ia : IA
    lateinit var appContext : Context
    lateinit var weatherForecast : WeatherForecast
    var searchCity = "Lyon"

    @Before
    fun init(){
        appContext = InstrumentationRegistry.getTargetContext()
        //DaggerComponent.builder().module(ModuleBase(appContext)).build().plus(this)
        DaggerComponentWeatherForecastList.builder().moduleWeatherForecastList(ModuleWeatherForecastList(appContext,searchCity,null)).build().plus(this)
        //DaggerComponentTestTest.builder().moduleTestTest(ModuleTestTest(appContext,searchCity,null)).build().plus(this)
        var coord = Coord(45.7578,4.832)
        var city = City(id = 2996944, name = "Lyon", coord = coord, country = "FR")
        var cod = "200"
        var list = ArrayList<WeatherForecastDay>()
        var weatherList0 = ArrayList<Weather>()
        var weatherList1 = ArrayList<Weather>()
        var weatherList2 = ArrayList<Weather>()
        var weatherList3 = ArrayList<Weather>()
        var weatherList4 = ArrayList<Weather>()
        var weatherList5 = ArrayList<Weather>()
        var weatherList6 = ArrayList<Weather>()

        var weather0 = Weather(id = 500,icon = "10d", description = "light rain",main = "Rain")
        var weather1 = Weather(id = 500,icon = "10d", description = "light rain",main = "Rain")
        var weather2 = Weather(id = 501,icon = "10d", description = "moderate rain",main = "Rain")
        var weather3 = Weather(id = 801,icon = "02d", description = "few clouds",main = "Clouds")
        var weather4 = Weather(id = 500,icon = "10d", description = "light rain",main = "Rain")
        var weather5 = Weather(id = 500,icon = "10d", description = "light rain",main = "Rain")
        var weather6 = Weather(id = 800,icon = "01d", description = "sky is clear",main = "Clear")

        weatherList0.add(weather0)
        weatherList1.add(weather1)
        weatherList2.add(weather2)
        weatherList3.add(weather3)
        weatherList4.add(weather4)
        weatherList5.add(weather5)
        weatherList6.add(weather6)


        var temp0 =  Temp(281.87,279.11,281.93,280.25,279.11,281.87)
        var temp1 = Temp(285.38,282.65,286.37,284.75,284.25,282.65)
        var temp2 = Temp(285.39,278.05,285.55,278.05,283.27,283.3)
        var temp3 = Temp(285.09,277.07,285.09,277.07,281.08,277.24)
        var temp4 = Temp(286.17,277.03,286.17,277.03,281.4,277.26)
        var temp5 = Temp(286.3,276.91,286.3,276.91,271.44,277.1)
        var temp6 = Temp(287.63,275.84,287.63,282.43,284.0,275.84)




        var weatherForecastDay0 = WeatherForecastDay(dt = 1540897200,temp = temp0,city = null, cloudss = 0, day = null, deg = 194, pressure = 274.86,rain = 0.29,snow = 0.0,speed = 6.27,humidity = 92,weather = weatherList0)
        var weatherForecastDay1 = WeatherForecastDay(dt = 1540983600,temp = temp1,city = null, cloudss = 24, day = null, deg = 147, pressure = 978.11,rain = 2.19,snow = 0.0,speed = 4.31,humidity = 90,weather = weatherList1)
        var weatherForecastDay2 = WeatherForecastDay(dt = 1541070000,temp = temp2,city = null, cloudss = 88, day = null, deg = 177, pressure = 980.8,rain = 7.56,snow = 0.0,speed = 5.01,humidity = 97,weather = weatherList2)
        var weatherForecastDay3 = WeatherForecastDay(dt = 1541156400,temp = temp3,city = null, cloudss = 20, day = null, deg = 11, pressure = 993.26,rain = 0.0,snow = 0.0,speed = 1.71,humidity = 98,weather = weatherList3)
        var weatherForecastDay4 = WeatherForecastDay(dt = 1541248200,temp = temp4,city = null, cloudss = 26, day = null, deg = 316, pressure = 986.59,rain = 0.0,snow = 0.0,speed = 1.8,humidity = 0,weather = weatherList4)
        var weatherForecastDay5 = WeatherForecastDay(dt = 1541329200,temp = temp5,city = null, cloudss = 40, day = null, deg = 335, pressure = 984.76,rain = 0.0,snow = 0.0,speed = 1.88,humidity = 0,weather = weatherList5)
        var weatherForecastDay6 = WeatherForecastDay(dt = 1541415600,temp = temp6,city = null, cloudss = 3, day = null, deg = 157, pressure = 982.67,rain = 0.0,snow = 0.0,speed = 1.8,humidity = 0,weather = weatherList6)

        list.add(weatherForecastDay0)
        list.add(weatherForecastDay1)
        list.add(weatherForecastDay2)
        list.add(weatherForecastDay3)
        list.add(weatherForecastDay4)
        list.add(weatherForecastDay5)
        list.add(weatherForecastDay6)

        weatherForecast = WeatherForecast(city = city, cod = cod, message = 1.5705601, cnt = 7, list = list)


        //var weatherForecastDay1 = WeatherForecastDay(dt = 1540983600,temp = temp,city = null, cloudss = 0, day = null, deg = 194, pressure = 274.86,rain = 0.29,snow = 0.0,speed = 6.27,humidity = 92,weather = weatherList)
        //TODO Write objects from JSON daily.json
    }

    @Test
    override fun updateWeatherForecastList() {

        networking.getWeatherForecastForOneWeekSearch(object : IApiResponse<WeatherForecast> {
            override fun onSuccess(obj: WeatherForecast?) {
                println("obj = ${obj}")
                if(appContext.packageName == "com.example.a727222.weatherapp.mock"){
                    assertEquals(obj,weatherForecast)
                }
            }

            override fun onError(t: Throwable) {
                println(t)
            }

        },searchCity)
    }

    @Test
    override fun updateWeatherForecastListSearch() {

    }
}