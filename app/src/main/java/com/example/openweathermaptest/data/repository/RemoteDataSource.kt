package com.example.openweathermaptest.data.repository

import com.example.openweathermaptest.data.api.WeatherApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val weatherApi: WeatherApi) {

    suspend fun getWeather(lat:Double,lon:Double) = weatherApi.getWeather(lat =lat, lon = lon)
}