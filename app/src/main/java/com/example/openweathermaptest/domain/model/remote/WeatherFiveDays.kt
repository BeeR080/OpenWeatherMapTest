package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import com.example.openweathermaptest.data.model.remoteDto.detail.CityDto
import com.example.openweathermaptest.data.model.remoteDto.detail.WeatherListDto
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherFiveDays(
    @SerializedName("city")
    val city: CityDto?,
    @SerializedName("list")
    val list: ArrayList<WeatherListDto>,

    ):Parcelable