package com.example.openweathermaptest.data.api

import com.example.openweathermaptest.data.model.WeatherFiveDays
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/forecast?")
    suspend fun getWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("appid") appid:String ="a6a1f5c8058a6a95902d7824996c8d2f"
    ):Response<WeatherFiveDays>
}