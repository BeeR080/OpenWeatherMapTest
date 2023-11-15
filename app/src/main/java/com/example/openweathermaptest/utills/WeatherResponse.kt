package com.example.openweathermaptest.utills

import retrofit2.Response
import java.lang.Exception

abstract class  WeatherResponse {

    suspend fun <T> safeRespons(response:suspend () -> Response<T>): WeatherResult<T> {

        try {

            val response = response()
            if(response.isSuccessful){

                val body = response.body()
                body.let {
                    WeatherResult.Loading(null)
                    return WeatherResult.Success(data = body)
                }


            }else{
                return WeatherResult.Error(data = null, message = response.message())
            }
        }catch (e:Exception){
            return WeatherResult.Error(data = null,message = e.message)
        }
    }
}