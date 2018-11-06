package com.example.a727222.weatherapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.a727222.weatherapp.interfaces.IApiResponse
import com.example.a727222.weatherapp.manager.DataManager
import com.example.a727222.weatherapp.models.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MockClientTest {
    @Test
    @Throws(Exception::class)
    fun getCurrentWeatherTest() {
        // Context of the app under test.
        val base = "stations"
        val visibility = 10000
        val dt = 1540818000
        val id = 2996944
        val name = "Lyon"
        val cod = 200

        val coord = Coord(45.76,4.83)
        val main = Main(277.38,276.15,278.15,992.0,humidity = 86,sea_level = 0.0,temp_kf = -1,grnd_level = 0.0)
        val main2 = Main()
        main2.temp = 277.38
        main2.temp_min = 276.15


        val wind = Wind(speed = 5.1,deg = 330.0)
        val clouds = Clouds(all = 92)
        val sys = Sys(type = 1,id = 5576, message = 0.0036,country = "FR",sunrise = 1540793816, sunset = 1540830665,pod = null)

        val weather0 = Weather(id = 501,main = "Rain", description = "moderate rain", icon = "10d")
        val weather1 = Weather(id = 701,main = "Mist", description = "mist", icon = "50d")
        val weather2 = Weather(id = 615,main = "Snow", description = "light rain and snow", icon = "13d")
        val weatherList = listOf(weather0,weather1,weather2)

        val weatherCurrent = WeatherCurrent(base = base,main = main,clouds = clouds,cod = cod,coord = coord,dt = dt,id = id,name = name,rain = null,snow = null,sys = sys,visibility = visibility,weather = weatherList,wind = wind)

        val appContext = InstrumentationRegistry.getTargetContext()
        var mockClient: MockClient = MockClient(context = appContext)
        var manager = DataManager(appContext, mockClient)
        manager.getCurrentWeather(object : IApiResponse<WeatherCurrent>{
            override fun onSuccess(obj: WeatherCurrent?) {
                assertEquals(weatherCurrent, obj)
            }
            override fun onError(t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
}