package com.example.openweathermaptest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.openweathermaptest.data.LocalDataSource
import com.example.openweathermaptest.data.RemoteDataSource
import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.data.model.remoteDto.main.WeatherFiveDaysDto
import com.example.openweathermaptest.domain.model.local.WeatherListLoc
import com.example.openweathermaptest.domain.model.remote.WeatherList
import com.example.openweathermaptest.domain.model.repository.WeatherRepository
import com.example.openweathermaptest.utills.WeatherResponse
import com.example.openweathermaptest.utills.WeatherResult

import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    ) : WeatherRepository,WeatherResponse() {
    override suspend fun getWeatherRemote(lat: Double, lon: Double): WeatherResult<WeatherFiveDaysDto> {
        return  safeRespons {
            remoteDataSource.getWeather(lat = lat, lon=lon)
        }
    }


    override suspend fun addWeather(weatherList: WeatherListLocDto) {
        localDataSource.addWeather(weatherList = weatherList)
    }

    override fun getWeatherLocal(): List<WeatherListLocDto> {
        return localDataSource.getWeather()
    }



}