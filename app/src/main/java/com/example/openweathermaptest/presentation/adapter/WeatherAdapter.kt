package com.example.openweathermaptest.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.openweathermaptest.R
import com.example.openweathermaptest.data.model.WeatherList
import com.example.openweathermaptest.databinding.WeatherListBinding
import com.example.openweathermaptest.utills.DateUtils
import com.example.openweathermaptest.utills.WeatherDiffUtil
import com.google.android.material.animation.AnimatableView.Listener

class WeatherAdapter(private val clickListener:OnItemClick): ListAdapter<WeatherList,WeatherAdapter.MyViewHolder>(WeatherDiffUtil()) {



   inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val binding = WeatherListBinding.bind(itemView)

        fun bind(weatherFiveDays: WeatherList) = with(binding){
            val currentItem = currentList[adapterPosition]
            mainfragTvDate.text = currentItem.dttTxt?.let { DateUtils.dateFormat(it) }
            mainfragTvHumidity.text = "${currentItem.main!!.humidity} %"
            mainfragTvTmin.text = "${currentItem.main.tempmin} °C"
            mainfragTvTmax.text = "${currentItem.main.tempmax} °C"
            mainfragTvPressure.text = "${currentItem.main.pressure} гПа"
            mainfragTvWindSpeed.text = "${currentItem.wind!!.speed} м/c"

        }

       init {
           binding.mainfragCard.setOnClickListener {
               clickListener.onClickItem(currentList[adapterPosition])
           }
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

    interface OnItemClick{
        fun onClickItem(weatherList: WeatherList)
    }



}