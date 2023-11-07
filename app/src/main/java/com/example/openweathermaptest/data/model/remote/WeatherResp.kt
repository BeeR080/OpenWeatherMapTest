package com.example.openweathermaptest.data.model.remote

import com.google.gson.annotations.SerializedName

data class WeatherResp(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: ArrayList<WeatherFiveDays>,
    @SerializedName("message")
    val message: Int?
)