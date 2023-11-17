package com.example.openweathermaptest.data.model.remoteDto.detail

import android.os.Parcelable
import com.example.openweathermaptest.domain.model.remote.Clouds
import com.example.openweathermaptest.domain.model.remote.Main
import com.example.openweathermaptest.domain.model.remote.Sys
import com.example.openweathermaptest.domain.model.remote.Weather
import com.example.openweathermaptest.domain.model.remote.Wind
import com.example.openweathermaptest.domain.model.remote.WeatherList
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
data class WeatherListDto(
    val clouds: Clouds?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("dt_txt")
    val dttTxt: String?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: ArrayList<Weather>?,
    @SerializedName("wind")
    val wind: Wind?,
):Parcelable{

    fun toWeatherList():WeatherList{
        return WeatherList(
            dt=dt,
            dttTxt = dttTxt,
            main = main,
            visibility = visibility,
            weather = weather,
            wind = wind,
            cityName = ""
        )

    }
}