package com.example.openweathermaptest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermaptest.R
import com.example.openweathermaptest.data.model.WeatherFiveDays
import com.example.openweathermaptest.databinding.WeatherListBinding
import com.example.openweathermaptest.utills.WeatherDiffUtil

class WeatherAdapter(): ListAdapter<WeatherFiveDays,WeatherAdapter.MyViewHolder>(WeatherDiffUtil()) {



   inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = WeatherListBinding.bind(itemView)

        fun bind(weatherFiveDays: WeatherFiveDays) = with(binding){
            val currentItem = weatherFiveDays.list[adapterPosition]
            mainfragTvDate.text = currentItem.dttTxt
            mainfragTvHumidity.text = currentItem.main!!.humidity.toString()
            mainfragTvTmin.text = currentItem.main.tempmin.toString()
            mainfragTvTmax.text = currentItem.main.tempmax.toString()
            mainfragTvPressure.text = currentItem.main.pressure.toString()
            mainfragTvWindSpeed.text = currentItem.wind!!.speed.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapter.MyViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(
            R.layout.weather_list,
            parent,
            false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: WeatherAdapter.MyViewHolder, position: Int) {
         holder.bind(getItem(position))


    }




}