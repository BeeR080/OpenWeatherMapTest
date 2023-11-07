package com.example.openweathermaptest.domain.usecases

import com.example.openweathermaptest.data.model.local.WeatherListLoc
import com.example.openweathermaptest.data.repository.WeatherRepository
import javax.inject.Inject

class AddWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend fun invoke(weatherList: WeatherListLoc) = repository.addWeather(weatherList = weatherList)
}