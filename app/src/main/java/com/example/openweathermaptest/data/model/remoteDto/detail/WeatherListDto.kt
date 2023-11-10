package com.example.openweathermaptest.data.model.remoteDto.detail

import android.os.Parcelable
import com.example.openweathermaptest.domain.model.remote.WeatherList
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize



@Parcelize
data class WeatherListDto(
    val clouds: CloudsDto?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("dt_txt")
    val dttTxt: String?,
    @SerializedName("main")
    val main: MainDto?,
    @SerializedName("pop")
    val pop: Double?,
    @SerializedName("sys")
    val sys: SysDto?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: ArrayList<WeatherDto>?,
    @SerializedName("wind")
    val wind: WindDto?,
):Parcelable{

    fun toWeatherList():WeatherList{
        return WeatherList(
            dt=dt,
            dttTxt = dttTxt,
            main = main,
            visibility = visibility,
            weather = weather,
            wind = wind
        )

    }
}