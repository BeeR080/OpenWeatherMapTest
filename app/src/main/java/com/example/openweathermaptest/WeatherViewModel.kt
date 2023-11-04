package com.example.openweathermaptest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweathermaptest.data.model.WeatherFiveDays
import com.example.openweathermaptest.domain.usecases.GetWeatherUseCase
import dagger.hilt.android.internal.lifecycle.HiltViewModelMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    ) :ViewModel(){




    private val _getWeather = MutableLiveData<WeatherResult<List<WeatherFiveDays>>>()
    val getWeather get() = _getWeather


    fun getWeather(lat:Double,lon:Double){
        viewModelScope.launch {
            getWeatherUseCase.invoke(lat = lat, lon = lon).let {weather->
                _getWeather.value = weather
            }

        }

    }


}