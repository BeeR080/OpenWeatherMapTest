package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import com.example.openweathermaptest.domain.model.local.WeatherListLoc
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.math.roundToInt


@Parcelize
data class WeatherList(
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("dt_txt")
    val dttTxt: String?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: ArrayList<Weather>?,
    @SerializedName("wind")
    val wind: Wind?,
):Parcelable{


    }



