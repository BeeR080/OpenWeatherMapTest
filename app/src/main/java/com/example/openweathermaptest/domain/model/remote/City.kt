package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import com.example.openweathermaptest.data.model.remoteDto.detail.CoordDto
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    @SerializedName("coord")
    val coord: CoordDto?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("population")
    val population: Int?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
    @SerializedName("timezone")
    val timezone: Int?
):Parcelable