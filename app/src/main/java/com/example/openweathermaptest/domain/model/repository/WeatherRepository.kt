package com.example.openweathermaptest.domain.model.repository

import androidx.lifecycle.LiveData
import com.example.openweathermaptest.data.model.remoteDto.main.WeatherFiveDaysDto
import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.utills.WeatherResult

interface WeatherRepository {

    suspend fun getWeatherRemote(lat:Double, lon:Double): WeatherResult<WeatherFiveDaysDto>
    suspend fun addWeather(weatherList: WeatherListLocDto)
    fun getWeatherLocal():LiveData<List<WeatherListLocDto>>

}