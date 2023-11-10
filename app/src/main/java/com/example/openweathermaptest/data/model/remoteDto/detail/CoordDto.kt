package com.example.openweathermaptest.data.model.remoteDto.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoordDto(
    @SerializedName("lat" )
    val lat: Double?,
    @SerializedName("lon" )
    val lon: Double?
):Parcelable