package com.example.openweathermaptest.data.model.remoteDto.detail

import com.example.openweathermaptest.data.model.remoteDto.main.WeatherFiveDaysDto
import com.google.gson.annotations.SerializedName

data class WeatherRespDto(
    @SerializedName("city")
    val city: CityDto?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: ArrayList<WeatherFiveDaysDto>,
    @SerializedName("message")
    val message: Int?
)