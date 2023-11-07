package com.example.openweathermaptest.data.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table ORDER BY id ASC")
    fun getWeather(): LiveData<List<WeatherListLoc>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeatherList(weatherList: WeatherListLoc)
}