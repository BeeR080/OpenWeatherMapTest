package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coord(
    @SerializedName("lat" )
    val lat: Double?,
    @SerializedName("lon" )
    val lon: Double?
):Parcelable