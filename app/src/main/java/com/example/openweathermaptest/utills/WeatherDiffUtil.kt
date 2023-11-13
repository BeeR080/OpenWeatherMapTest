package com.example.openweathermaptest.utills

import androidx.recyclerview.widget.DiffUtil
import com.example.openweathermaptest.data.model.remoteDto.detail.WeatherListDto
import com.example.openweathermaptest.domain.model.remote.WeatherList

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherList>(){


    override fun areItemsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
      return  oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: WeatherList, newItem: WeatherList): Boolean {
        return oldItem == newItem
    }


}