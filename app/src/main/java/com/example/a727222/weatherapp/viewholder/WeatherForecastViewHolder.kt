package com.example.a727222.weatherapp.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.utils.Utils


class WeatherForecastViewHolder(itemView: View, onItemClickListener: OnItemWeatherForecastClickListener?) : WeatherForecastBaseViewHolder<ForecastItem>(itemView), View.OnClickListener{

    val forecastDay = itemView.findViewById<TextView>(R.id.recycler_view_forecast_day_textView)
    val forecastIcon = itemView.findViewById<ImageView>(R.id.recycler_view_forecast_icon_imageView)
    val forecastTemperatureMax = itemView.findViewById<TextView>(R.id.recycler_view_forecast_temperature_max)
    val forecastTemperatureMin = itemView.findViewById<TextView>(R.id.recycler_view_forecast_temperature_min)
    val onItemClickListener: OnItemWeatherForecastClickListener?
    val context = itemView.context

    init {
        this.onItemClickListener = onItemClickListener
        itemView.setOnClickListener(this)
    }


    override fun fill(weatherForecastModel: ForecastItem) {
        forecastDay.text = weatherForecastModel.day
        forecastIcon.setImageResource(Utils.getTypeIconId(weatherForecastModel.icon.toString()))
        forecastTemperatureMax.text = weatherForecastModel.temperature_max.toString() + context.getString(R.string.weather_activity_unit_degree)
        forecastTemperatureMin.text = weatherForecastModel.temperature_min.toString() + context.getString(R.string.weather_activity_unit_degree)
    }


    override fun onClick(v: View?) {
        onItemClickListener?.onItemWeatherClick(adapterPosition)
    }




}


