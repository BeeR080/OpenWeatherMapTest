package com.example.openweathermaptest.data

import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val weatherListLocDao: WeatherDao) {

   fun addWeather(weatherList: WeatherListLocDto) =
        weatherListLocDao.addWeatherList(weatherList)

    fun getWeather()= weatherListLocDao.getWeather()
}