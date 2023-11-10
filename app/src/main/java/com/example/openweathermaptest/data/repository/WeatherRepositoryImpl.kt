package com.example.openweathermaptest.data.repository

import androidx.lifecycle.LiveData
import com.example.openweathermaptest.data.LocalDataSource
import com.example.openweathermaptest.data.RemoteDataSource
import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.domain.model.repository.WeatherRepository
import com.example.openweathermaptest.utills.WeatherResponse

import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    ) : WeatherRepository,WeatherResponse() {
    override suspend fun getWeatherRemote(lat: Double, lon: Double) =
      safeRespons {
          remoteDataSource.getWeather(lat = lat, lon=lon)
      }





    override suspend fun addWeather(weatherList: WeatherListLocDto) {
        localDataSource.addWeather(weatherList = weatherList)
    }

    override fun getWeatherLocal(): LiveData<List<WeatherListLocDto>> =
        localDataSource.getWeather()


}