package com.example.openweathermaptest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.openweathermaptest.domain.model.local.WeatherListLoc
import com.example.openweathermaptest.domain.model.remote.WeatherFiveDays
import com.example.openweathermaptest.utills.WeatherResult
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




    private val _getWeather = MutableLiveData<WeatherFiveDays?>()
    val getWeather get() = _getWeather

    private val _getLocWeather = MutableLiveData<List<WeatherListLoc>>()
    val getLocWeather get() = _getLocWeather


    fun getWeather(lat:Double,lon:Double){
        viewModelScope.launch {
            getWeatherUseCase.invoke(lat = lat, lon = lon).let {result->
                when(result){
                    is WeatherResult.Success->{
                        _getWeather.value = result.data.let {
                            it?.toWeatherFiveDays()
                        }
                    }
                    is WeatherResult.Error->{

                    }
                    else -> {}
                }


            }

        }

    }

    fun getWeatherLoc(){
        viewModelScope.launch {
            getLocWeatherUseCase.invoke().let { data->
                _getLocWeather.value = data.value?.map { it.toWeatherList() }
            }
        }
    }




    suspend fun addWeather(weatherList: WeatherListLoc){
        viewModelScope.launch(Dispatchers.IO) {
            addWeatherUseCase.invoke(weatherList.let {
            it.toWeatherListDto()
            })
        }

    }

}