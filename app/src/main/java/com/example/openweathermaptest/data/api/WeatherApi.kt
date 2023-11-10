package com.example.openweathermaptest.data.api

import com.example.openweathermaptest.data.model.remoteDto.main.WeatherFiveDaysDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


private const val APP_ID ="a6a1f5c8058a6a95902d7824996c8d2f"
interface WeatherApi {
    @GET("/data/2.5/forecast?")
    suspend fun getWeather(
        @Query("lat") lat:Double,
        @Query("lon") lon:Double,
        @Query("units") units:String = "metric",
        @Query("lang") lang:String = "ru",
        @Query("appid") appid:String = APP_ID
    ):Response<WeatherFiveDaysDto>
}