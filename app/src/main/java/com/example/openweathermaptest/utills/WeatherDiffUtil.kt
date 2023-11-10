package com.example.openweathermaptest.utills

import androidx.recyclerview.widget.DiffUtil
import com.example.openweathermaptest.data.model.remoteDto.detail.WeatherListDto

class WeatherDiffUtil : DiffUtil.ItemCallback<WeatherListDto>(){


    override fun areItemsTheSame(oldItem: WeatherListDto, newItem: WeatherListDto): Boolean {
      return  oldItem.dt == newItem.dt
    }

    override fun areContentsTheSame(oldItem: WeatherListDto, newItem: WeatherListDto): Boolean {
        return oldItem == newItem
    }


}