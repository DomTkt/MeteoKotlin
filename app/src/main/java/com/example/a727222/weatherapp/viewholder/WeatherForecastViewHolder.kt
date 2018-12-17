package com.example.a727222.weatherapp.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.utils.Utils

/**
 * This class is the ViewHolder of weather forecast, allows to fill data
 */
class WeatherForecastViewHolder(itemView: View, onItemClickListener: OnItemWeatherForecastClickListener?) : WeatherForecastBaseViewHolder<ForecastItem>(itemView), View.OnClickListener{

    private val forecastDay = itemView.findViewById<TextView>(R.id.recycler_view_forecast_day_textView)
    private val forecastIcon = itemView.findViewById<ImageView>(R.id.recycler_view_forecast_icon_imageView)
    private val forecastTemperatureMax = itemView.findViewById<TextView>(R.id.recycler_view_forecast_temperature_max)
    private val forecastTemperatureMin = itemView.findViewById<TextView>(R.id.recycler_view_forecast_temperature_min)
    private val onItemClickListener: OnItemWeatherForecastClickListener? = onItemClickListener
    private val context = itemView.context

    init {
        itemView.setOnClickListener(this)
    }


    /**
     * This method allows to fill the model
     * @param weatherForecastModel is the model give in parameter class in WeatherForecastBaseViewHolder which is implemented by the current class
     */
    override fun fill(weatherForecastModel: ForecastItem) {
        forecastDay.text = weatherForecastModel.day
        forecastIcon.setImageResource(Utils.getTypeIconId(weatherForecastModel.icon.toString()))
        forecastTemperatureMax.text = weatherForecastModel.temperature_max.toString() + context.getString(R.string.weather_activity_unit_degree)
        forecastTemperatureMin.text = weatherForecastModel.temperature_min.toString() + context.getString(R.string.weather_activity_unit_degree)
    }


    /**
     * @param v View
     */
    override fun onClick(v: View?) {
        onItemClickListener?.onItemWeatherClick(adapterPosition)
    }
}


