package com.example.openweathermaptest.data.model

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat" )
    val lat: Double?,
    @SerializedName("lon" )
    val lon: Double?
)