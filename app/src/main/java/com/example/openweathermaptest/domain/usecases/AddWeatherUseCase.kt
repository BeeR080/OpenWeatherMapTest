package com.example.openweathermaptest.domain.usecases


import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.domain.model.local.WeatherListLoc
import com.example.openweathermaptest.domain.model.repository.WeatherRepository

import javax.inject.Inject

class AddWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {

    suspend fun invoke(weatherList: WeatherListLocDto) =
        repository.addWeather(weatherList = weatherList)
}