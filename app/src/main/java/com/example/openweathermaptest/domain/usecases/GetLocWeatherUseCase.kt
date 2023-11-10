package com.example.openweathermaptest.domain.usecases

import com.example.openweathermaptest.domain.model.repository.WeatherRepository
import javax.inject.Inject

class GetLocWeatherUseCase @Inject constructor(private val repository: WeatherRepository) {

     fun invoke() = repository.getWeatherLocal()
}