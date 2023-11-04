package com.example.openweathermaptest.data.model

data class WeatherFiveDays(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Weather>,
    val message: Int
)