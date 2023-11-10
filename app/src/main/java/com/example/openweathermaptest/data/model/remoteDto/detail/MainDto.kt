package com.example.openweathermaptest.data.model.remoteDto.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainDto(
    @SerializedName("feels_like")
    val feelslike: Double?,
    @SerializedName("grnd_level")
    val grndlevel: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("sea_level")
    val sealevel: Int?,
    @SerializedName("temp" )
    val temp: Double?,
    @SerializedName("temp_kf")
    val tempkf: Double?,
    @SerializedName("temp_max")
    val tempmax: Double?,
    @SerializedName("temp_min")
    val tempmin: Double?
):Parcelable