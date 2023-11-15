package com.example.openweathermaptest.domain.model.remote

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.example.openweathermaptest.domain.model.local.WeatherListLoc
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.math.roundToInt


@Parcelize
data class WeatherFiveDays(
    @SerializedName("city")
    val city: City?,
    @SerializedName("list")
    val list: ArrayList<WeatherList>,

    ):Parcelable{

    fun toWeatherListLoc():MutableList<WeatherListLoc> {
        val mutable = mutableListOf<WeatherListLoc>()
      if (list.isNotEmpty()){
        for (elements in list) {
           mutable.add(WeatherListLoc(
                id = elements.dt!!.toInt(),
                date = elements.dttTxt!!,
                humidity = elements.main?.humidity.toString(),
                tMin = elements.main?.tempmin!!.roundToInt(),
                tMax = elements.main.tempmax!!.roundToInt(),
                tCur = elements.main.temp!!.roundToInt(),
                pressure = elements.main.pressure!!,
                speed = elements.wind?.speed!!.roundToInt(),
                cityName = city?.name.toString(),
                feelsLike = elements.main.feelslike!!.roundToInt(),
                visibility = elements.visibility!!.toInt(),
                description = elements.weather?.get(0)?.description.toString()

            ))
        }

    }
        return mutable
    }
         }





