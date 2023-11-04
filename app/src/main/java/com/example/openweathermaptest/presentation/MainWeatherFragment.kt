package com.example.openweathermaptest.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.openweathermaptest.WeatherResult
import com.example.openweathermaptest.WeatherViewModel
import com.example.openweathermaptest.databinding.FragmentMainWeatherBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainWeatherFragment : Fragment() {

    private var _binding : FragmentMainWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherVM by viewModels<WeatherViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainWeatherBinding.inflate(inflater, container, false)
        getWeather(	55.7522,37.6156)

        return binding.root
    }




    private fun getWeather(lat:Double,lon:Double){
        weatherVM.getWeather(lat = lat,lon= lon)
        weatherVM.getWeather.observe(viewLifecycleOwner){weather->
    when(weather){

        is WeatherResult.Success ->{
            Log.d("MyLog",weather.toString())
        }

        is WeatherResult.Error ->{}

        is WeatherResult.Loading -> {}
    }

        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }


}