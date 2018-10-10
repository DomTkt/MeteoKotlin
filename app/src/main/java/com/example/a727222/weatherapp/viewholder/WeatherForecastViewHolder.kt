package com.example.a727222.weatherapp.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.a727222.weatherapp.R

class WeatherForecastViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val forecastDay = itemView.findViewById<TextView>(R.id.recycler_view_forecast_day_textView)
}