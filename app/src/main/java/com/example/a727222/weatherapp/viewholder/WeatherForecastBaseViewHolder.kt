package com.example.a727222.weatherapp.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * This class allow to fill viewholder regardless of the model
 */
abstract class WeatherForecastBaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    /**
     * fill the model that you want
     */
    abstract fun fill(weatherForecastModel: T)
}