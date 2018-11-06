package com.example.a727222.weatherapp.utils

import com.example.a727222.weatherapp.R
import junit.framework.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun convertKelvinToCelsius() {
        var actual : Int? = Utils.convertKelvinToCelsius(0.00)
        var expected : Int? = -273
        assertEquals("Conversion from Kelvin to Celsius",actual,expected)
    }

    @Test
    fun convertTimeStampInSuntimes() {
        var actual : String = Utils.convertTimeStampInSuntimes(1540908682)
        var expected : String = "15:11"
        assertEquals("convert from time stamp to suntimes",actual,expected)
    }

    @Test
    fun getTypeIconId() {
        var actual : Int = Utils.getTypeIconId("01d")
        var expected : Int = R.drawable.clear_sky
        assertEquals("get icon type",actual, expected)
    }

}