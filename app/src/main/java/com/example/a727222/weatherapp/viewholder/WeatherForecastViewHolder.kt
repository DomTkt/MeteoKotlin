package com.example.a727222.weatherapp.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.utils.Utils


class WeatherForecastViewHolder(itemView: View, onItemClickListener: OnItemWeatherForecastClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    val forecastDay = itemView.findViewById<TextView>(R.id.recycler_view_forecast_day_textView)
    val forecastIcon = itemView.findViewById<ImageView>(R.id.recycler_view_forecast_icon_imageView)
    val forecastTemperatureMax = itemView.findViewById<TextView>(R.id.recycler_view_forecast_temperature_max)
    val forecastTemperatureMin = itemView.findViewById<TextView>(R.id.recycler_view_forecast_temperature_min)
    val onItemClickListener: OnItemWeatherForecastClickListener?

    init {
        this.onItemClickListener = onItemClickListener
        itemView.setOnClickListener(this)
    }

    fun bind(item : ForecastItem,context : Context){
        forecastDay.text = item.day
        forecastIcon.setImageResource(Utils.getTypeIconId(item.icon.toString()))
        forecastTemperatureMax.text = item.temperature_max.toString() + context.getString(R.string.weather_activity_unit_degree)
        forecastTemperatureMin.text = item.temperature_min.toString() + context.getString(R.string.weather_activity_unit_degree)
    }

    override fun onClick(v: View?) {
        onItemClickListener?.onItemWeatherClick(adapterPosition)
    }




}


