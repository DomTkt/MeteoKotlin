package com.example.a727222.weatherapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.viewholder.WeatherForecastViewHolder



class WeatherForecastAdapter(val items : ArrayList<ForecastItem>, clickListener : OnItemWeatherForecastClickListener) : RecyclerView.Adapter<WeatherForecastViewHolder>() {

    private var onItemClickListener: OnItemWeatherForecastClickListener?

    init {
        onItemClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        return WeatherForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_forecast_list_item, parent, false),onItemClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.fill(items.get(position))
    }

}

