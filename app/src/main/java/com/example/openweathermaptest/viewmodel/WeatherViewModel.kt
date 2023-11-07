package com.example.openweathermaptest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweathermaptest.WeatherResult
import com.example.openweathermaptest.data.model.local.WeatherListLoc
import com.example.openweathermaptest.data.model.remote.WeatherFiveDays
import com.example.openweathermaptest.domain.usecases.AddWeatherUseCase
import com.example.openweathermaptest.domain.usecases.GetLocWeatherUseCase
import com.example.openweathermaptest.domain.usecases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val addWeatherUseCase: AddWeatherUseCase,
    private val getLocWeatherUseCase: GetLocWeatherUseCase
    ) :ViewModel(){




    private val _getWeather = MutableLiveData<WeatherResult<WeatherFiveDays>>()
    val getWeather get() = _getWeather

    private val _getLocWeather = getLocWeatherUseCase.invoke()
    val getLocWeather get() = _getLocWeather


    fun getWeather(lat:Double,lon:Double){
        viewModelScope.launch {
            getWeatherUseCase.invoke(lat = lat, lon = lon).let {weather->

                _getWeather.value = weather
            }

        }

    }




    suspend fun addWeather(weatherList: WeatherListLoc){
        viewModelScope.launch(Dispatchers.IO) {
            addWeatherUseCase.invoke(weatherList)
        }

    }


}