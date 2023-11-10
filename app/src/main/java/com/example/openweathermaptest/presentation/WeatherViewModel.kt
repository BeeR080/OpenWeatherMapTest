package com.example.openweathermaptest.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweathermaptest.utills.WeatherResult
import com.example.openweathermaptest.data.model.localDto.main.WeatherListLocDto
import com.example.openweathermaptest.data.model.remoteDto.main.WeatherFiveDaysDto
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
    private val getLocWeatherUseCase: GetLocWeatherUseCase,
    ) :ViewModel(){




    private val _getWeather = MutableLiveData<WeatherFiveDaysDto?>()


    val getWeather get() = _getWeather

    private val _getLocWeather = getLocWeatherUseCase.invoke()
    val getLocWeather get() = _getLocWeather


    fun getWeather(lat:Double,lon:Double){
        viewModelScope.launch {
            getWeatherUseCase.invoke(lat = lat, lon = lon).let {result->
                when(result){
                    is WeatherResult.Success->{
                        _getWeather.value = result.data
                    }
                    is WeatherResult.Error->{
                     _getWeather.value = null
                    }
                    is WeatherResult.Loading->{}
                }


            }

        }

    }




    suspend fun addWeather(weatherList: WeatherListLocDto){
        viewModelScope.launch(Dispatchers.IO) {
            addWeatherUseCase.invoke(weatherList)
        }

    }


}