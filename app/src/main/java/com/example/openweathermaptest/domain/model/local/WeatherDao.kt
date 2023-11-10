package com.example.openweathermaptest.domain.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto


@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table ORDER BY id ASC")
    fun getWeather(): LiveData<List<WeatherListLocDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addWeatherList(weatherList: WeatherListLocDto)
}