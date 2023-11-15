package com.example.openweathermaptest.data.model.localDto.main

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.openweathermaptest.domain.model.remote.Main
import com.example.openweathermaptest.domain.model.remote.Weather
import com.example.openweathermaptest.domain.model.remote.WeatherList
import com.example.openweathermaptest.domain.model.remote.Wind
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

    fun toWeatherList():WeatherList{

        return WeatherList(
            dt = id,
            dttTxt = date,
            main = Main(
                feelslike = feelsLike.toDouble(),
                grndlevel = null,
                humidity = humidity.toInt(),
                pressure = pressure,
                sealevel = null,
                temp = tCur.toDouble(),
                tempkf = null,
                tempmax = tMax.toDouble(),
                tempmin = tMin.toDouble()
            ),
            visibility = visibility,
            weather = arrayListOf( Weather(
                description = description,
                icon = null,
                id = null,
                main = null
            )),
            wind = Wind(
                deg = null,
                gust = null,
                speed = speed.toDouble()
                )

        )
    }
}









