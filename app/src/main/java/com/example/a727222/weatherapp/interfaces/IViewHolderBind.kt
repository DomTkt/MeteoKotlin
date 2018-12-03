package com.example.a727222.weatherapp.interfaces

import android.content.Context

interface IViewHolderBind<T> {
    fun bind(from : T, context : Context)
}