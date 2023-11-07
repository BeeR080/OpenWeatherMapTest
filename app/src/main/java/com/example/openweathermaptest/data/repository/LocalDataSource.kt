package com.example.openweathermaptest.data.repository

import androidx.lifecycle.LiveData
import com.example.openweathermaptest.data.model.local.WeatherDao
import com.example.openweathermaptest.data.model.local.WeatherListLoc
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val weatherListLocDao: WeatherDao) {

    suspend fun addWeather(weatherList: WeatherListLoc) =
        weatherListLocDao.addWeatherList(weatherList)

    fun getWeather()= weatherListLocDao.getWeather()
}