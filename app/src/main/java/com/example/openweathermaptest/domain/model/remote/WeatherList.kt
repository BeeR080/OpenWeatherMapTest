package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import com.example.openweathermaptest.data.model.remoteDto.detail.CloudsDto
import com.example.openweathermaptest.data.model.remoteDto.detail.MainDto
import com.example.openweathermaptest.data.model.remoteDto.detail.SysDto
import com.example.openweathermaptest.data.model.remoteDto.detail.WeatherDto
import com.example.openweathermaptest.data.model.remoteDto.detail.WindDto
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
data class WeatherList(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("dt_txt")
    val dttTxt: String?,
    @SerializedName("main")
    val main: MainDto?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: ArrayList<WeatherDto>?,
    @SerializedName("wind")
    val wind: WindDto?,
):Parcelable