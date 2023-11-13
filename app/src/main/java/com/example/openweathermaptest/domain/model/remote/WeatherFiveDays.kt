package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherFiveDays(
    @SerializedName("city")
    val city: City?,
    @SerializedName("list")
    val list: ArrayList<WeatherList>,

    ):Parcelable