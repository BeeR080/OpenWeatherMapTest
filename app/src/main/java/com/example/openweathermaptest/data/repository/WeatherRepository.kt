package com.example.openweathermaptest.data.repository

import com.example.openweathermaptest.WeatherResponse
import com.example.openweathermaptest.WeatherResult
import com.example.openweathermaptest.data.api.WeatherApi
import com.example.openweathermaptest.data.model.WeatherFiveDays
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
):WeatherResponse() {

    suspend fun getWeather(lat:Double,lon:Double):WeatherResult<List<WeatherFiveDays>>{

        return safeRespons {
            remoteDataSource.getWeather(lat = lat, lon = lon)
        }

    }

}