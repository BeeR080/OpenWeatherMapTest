package com.example.openweathermaptest.data.model.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize



@Parcelize
@Entity(tableName = "weather_table")
data class WeatherListLoc(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val date:String,
    val humidity:String,
    val tMin:Int,
    val tMax:Int,
    val pressure:Int,
    val speed:Int,


):Parcelable








