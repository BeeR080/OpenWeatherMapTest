package com.example.openweathermaptest.data.model.localDto.main

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
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



):Parcelable



fun toWeatherList(){}





