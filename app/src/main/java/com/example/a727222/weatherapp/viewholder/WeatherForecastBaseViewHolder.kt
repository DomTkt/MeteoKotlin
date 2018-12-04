package com.example.a727222.weatherapp.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class WeatherForecastBaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun fill(weatherForecastModel: T)
}