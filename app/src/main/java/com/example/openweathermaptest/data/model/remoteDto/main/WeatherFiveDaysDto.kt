package com.example.openweathermaptest.data.model.remoteDto.main

import android.os.Parcelable
import com.example.openweathermaptest.domain.model.remote.WeatherFiveDays
import com.example.openweathermaptest.domain.model.remote.City
import com.example.openweathermaptest.domain.model.remote.WeatherList
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherFiveDaysDto(
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
):Parcelable{

    fun toWeatherFiveDays():WeatherFiveDays{
        return WeatherFiveDays(
            city = city,
            list = list,
        )
    }



}



