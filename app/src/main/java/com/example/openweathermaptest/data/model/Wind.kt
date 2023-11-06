package com.example.openweathermaptest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Wind(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?
):Parcelable