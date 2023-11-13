package com.example.openweathermaptest.data

import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.domain.model.local.WeatherDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val weatherListLocDao: WeatherDao) {

   fun addWeather(weatherList: WeatherListLocDto) =
        weatherListLocDao.addWeatherList(weatherList)

    fun getWeather()= weatherListLocDao.getWeather()
}