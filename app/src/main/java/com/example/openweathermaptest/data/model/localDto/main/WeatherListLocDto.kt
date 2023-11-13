package com.example.openweathermaptest.data.model.localDto.main

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.openweathermaptest.domain.model.local.WeatherListLoc
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "weather_table")
data class WeatherListLocDto(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val date:String,
    val humidity:String,
    val tMin:Int,
    val tMax:Int,
    val tCur:Int,
    val pressure:Int,
    val speed:Int,
    val cityName:String,
    val feelsLike:Int,
    val visibility: Int,
    val description:String,



):Parcelable{
    fun toWeatherList():WeatherListLoc{
        return WeatherListLoc(
            id = id,
            date = date,
            humidity = humidity,
            tMin = tMin,
            tMax = tMax,
            tCur = tCur,
            pressure = pressure,
            speed = speed,
            cityName = cityName,
            feelsLike = feelsLike,
            visibility = visibility,
            description = description

            )

    }
}









