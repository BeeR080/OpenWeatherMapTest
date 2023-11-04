package com.example.openweathermaptest

sealed class WeatherResult<T> (
    val data :T? = null,
    val message:String? = null
){

 class Success<T>(data:T?): WeatherResult<T>(data = data)
 class Error<T>(data: T?,message: String?): WeatherResult<T>(data = data,message = message)
 class Loading<T>():WeatherResult<T>()

}