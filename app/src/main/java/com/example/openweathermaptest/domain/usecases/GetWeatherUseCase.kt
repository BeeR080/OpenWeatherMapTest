package com.example.openweathermaptest.domain.usecases

import com.example.openweathermaptest.domain.model.repository.WeatherRepository

import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

    suspend fun invoke(lat:Double,lon:Double) = repository.getWeatherRemote(lat = lat,lon = lon)


      }







