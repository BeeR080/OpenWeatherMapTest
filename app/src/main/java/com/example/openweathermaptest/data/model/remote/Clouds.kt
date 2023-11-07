package com.example.openweathermaptest.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Clouds(
    @SerializedName("all")
    val all: Int?
):Parcelable