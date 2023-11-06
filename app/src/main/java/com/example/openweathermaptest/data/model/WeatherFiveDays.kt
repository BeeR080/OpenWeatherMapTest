package com.example.openweathermaptest.data.model

import com.google.gson.annotations.SerializedName

data class WeatherFiveDays(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: ArrayList<WeatherList>,
    @SerializedName("message")
    val message: Int?
)