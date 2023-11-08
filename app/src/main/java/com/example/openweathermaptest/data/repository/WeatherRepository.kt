package com.example.openweathermaptest.data.repository

import com.example.openweathermaptest.utills.WeatherResponse
import com.example.openweathermaptest.utills.WeatherResult
import com.example.openweathermaptest.data.model.local.WeatherListLoc
import com.example.openweathermaptest.data.model.remote.WeatherFiveDays
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): WeatherResponse() {

    suspend fun getWeather(lat:Double,lon:Double): WeatherResult<WeatherFiveDays> {

        return safeRespons {
            remoteDataSource.getWeather(lat = lat, lon = lon)
        }

    }
    suspend fun addWeather(weatherList: WeatherListLoc) = localDataSource.addWeather(weatherList)

     fun getWeather() = localDataSource.getWeather()

}