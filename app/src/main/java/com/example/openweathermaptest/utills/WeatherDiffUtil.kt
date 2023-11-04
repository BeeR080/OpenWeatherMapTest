package com.example.openweathermaptest.utills

import androidx.recyclerview.widget.DiffUtil
import com.example.openweathermaptest.data.model.WeatherFiveDays

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherFiveDays>(){


    override fun areItemsTheSame(oldItem: WeatherFiveDays, newItem: WeatherFiveDays): Boolean {
      return  oldItem.list == newItem.list
    }

    override fun areContentsTheSame(oldItem: WeatherFiveDays, newItem: WeatherFiveDays): Boolean {
        return oldItem == newItem
    }


}