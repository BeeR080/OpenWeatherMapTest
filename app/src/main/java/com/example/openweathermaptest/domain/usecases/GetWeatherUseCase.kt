package com.example.openweathermaptest.domain.usecases

import com.example.openweathermaptest.data.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend fun invoke(lat:Double,lon:Double)= repository.getWeather(lat = lat,lon = lon)

}