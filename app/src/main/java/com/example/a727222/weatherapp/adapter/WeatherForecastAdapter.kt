package com.example.a727222.weatherapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.a727222.weatherapp.R
import com.example.a727222.weatherapp.interfaces.OnItemWeatherForecastClickListener
import com.example.a727222.weatherapp.models.ForecastItem
import com.example.a727222.weatherapp.viewholder.WeatherForecastViewHolder


/**
 * @param items contains the elements of type ForecastItem to display
 * @param clickListener allows to get the item click on the list
 *
 */
class WeatherForecastAdapter(val items : ArrayList<ForecastItem>, clickListener : OnItemWeatherForecastClickListener) : RecyclerView.Adapter<WeatherForecastViewHolder>() {
    /**
     * onItemClickerListener variable interface OnItemWeatherForecastClickListener
     */
    private var onItemClickListener: OnItemWeatherForecastClickListener?

    init {
        onItemClickListener = clickListener
    }

    /**
     * @param parent
     * @param viewType
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastViewHolder {
        return WeatherForecastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_forecast_list_item, parent, false),onItemClickListener)
    }

    /**
     * @return the size of array list which is in parameter in adapter
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * @param holder
     * @param position position of the cell
     * filled each cell according to the position of the item in the list
     */
    override fun onBindViewHolder(holder: WeatherForecastViewHolder, position: Int) {
        holder.fill(items.get(position))
    }


}

