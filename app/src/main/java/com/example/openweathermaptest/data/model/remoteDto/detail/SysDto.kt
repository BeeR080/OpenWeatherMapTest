package com.example.openweathermaptest.data.model.remoteDto.detail

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SysDto(
    @SerializedName("pod")
    val pod: String?
):Parcelable