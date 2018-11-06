package com.example.a727222.weatherapp.daggerTestDeleteAfter

import java.util.*


class MyExampleImpl : MyExample {

    private val mDate: Long

    init {
        mDate = Date().getTime()
    }

    override fun getDate(): Long {
        return mDate
    }

}